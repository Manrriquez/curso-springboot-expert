package io.github.manrriquez.vendas.services.ServiceImpl;


import io.github.manrriquez.vendas.enums.StatusDemand;
import io.github.manrriquez.vendas.exceptions.DemandNotFoundException;
import io.github.manrriquez.vendas.exceptions.RuleBusinessException;
import io.github.manrriquez.vendas.Repositories.ClientRepository;
import io.github.manrriquez.vendas.Repositories.DemandRepository;
import io.github.manrriquez.vendas.Repositories.ItemDemandRepository;
import io.github.manrriquez.vendas.Repositories.ProductRepository;
import io.github.manrriquez.vendas.dtos.DemandDTO;
import io.github.manrriquez.vendas.dtos.ItemDemandDTO;
import io.github.manrriquez.vendas.models.ClientModel;
import io.github.manrriquez.vendas.models.DemandModel;
import io.github.manrriquez.vendas.models.ItemDemandModel;
import io.github.manrriquez.vendas.models.ProductModel;
import io.github.manrriquez.vendas.services.DemandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DemandServiceImpl implements DemandService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DemandRepository demandRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemDemandRepository itemDemandRepository;

    @Override
    @Transactional
    public DemandModel save(DemandDTO dto) {
        Long idClient = dto.getClient();
        ClientModel client =  clientRepository.findById(idClient)
                .orElseThrow(() -> new RuleBusinessException("Codigo de cliente inválido!"));
        DemandModel demand = new DemandModel();
        demand.setAmount(dto.getAmount());
        demand.setData_demand(LocalDate.now());
        demand.setClient(client);
        demand.setStatus(StatusDemand.REALIZADO);

        List<ItemDemandModel> itemsDemand = convertItems(demand, dto.getItems());
        demandRepository.save(demand);
        itemDemandRepository.saveAll(itemsDemand);
        demand.setItems(itemsDemand);
        return demand;
    }


    //OBTER PEDIDO COMPLETO
    @Override
    public Optional<DemandModel> getFullOrder(Long id) {
        return demandRepository.findByFetchItems(id);
    }

    @Override
    @Transactional
    public void updateStatus(Long id, StatusDemand statusDemand) {

        demandRepository.findById(id)
                .map(demand -> {
                    demand.setStatus(statusDemand);
                    return demandRepository.save(demand);
                }).orElseThrow(() -> new DemandNotFoundException());
    }

    public List<ItemDemandModel> convertItems(DemandModel demand, List<ItemDemandDTO> items) {

        if(items.isEmpty()) {
            throw new RuleBusinessException("Não e possivel realizar pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Long idProduct = dto.getProduct();
                    ProductModel product = productRepository
                            .findById(idProduct)
                            .orElseThrow(() -> new RuleBusinessException("Código de produto invalido: " + idProduct));
                    ItemDemandModel itemDemand = new ItemDemandModel();
                    itemDemand.setAmount(dto.getAmount());
                    itemDemand.setDemand(demand);
                    itemDemand.setProduct(product);

                    return itemDemand;
                }).collect(Collectors.toList());
    }
}
