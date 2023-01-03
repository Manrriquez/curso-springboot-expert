package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.enums.StatusDemand;
import io.github.manrriquez.vendas.dtos.DemandDTO;
import io.github.manrriquez.vendas.dtos.DemandInformationsDTO;
import io.github.manrriquez.vendas.dtos.DemandItemInformationDTO;
import io.github.manrriquez.vendas.dtos.DemandStatusUpdateDTO;
import io.github.manrriquez.vendas.models.DemandModel;
import io.github.manrriquez.vendas.models.ItemDemandModel;
import io.github.manrriquez.vendas.services.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pedidos")
public class DemandController {

    @Autowired
    private DemandService demandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long saveDemand(@RequestBody @Valid DemandDTO dto) {

        DemandModel demand = demandService.save(dto);

        return demand.getId();
    }
    @GetMapping("{id}")
    public DemandInformationsDTO getById(@PathVariable Long id) {

        return  demandService.getFullOrder(id)
                .map(demand -> converter(demand))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Long id, @RequestBody DemandStatusUpdateDTO dto) {

        String newStatus = dto.getNewStatus();
        demandService.updateStatus(id, StatusDemand.valueOf(newStatus));
    }
    private DemandInformationsDTO converter(DemandModel demand) {

        return DemandInformationsDTO
                .builder()
                .code(demand.getId())
                .dateDemand(demand.getData_demand().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(demand.getClient().getCpf())
                .nameClient(demand.getClient().getName())
                .amount(demand.getAmount())
                .status(demand.getStatus().name())
                .items(converter(demand.getItems()))
                .build();
    }

    private List<DemandItemInformationDTO> converter(List<ItemDemandModel> items) {

        if(CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }

        return items.stream().map(item ->
                DemandItemInformationDTO.builder()
                        .descriptionProduct(item.getProduct().getDescription())
                        .amountUnity(item.getProduct().getAmount())
                        .amount(item.getAmount())
                        .build()
        ).collect(Collectors.toList());
    }
}
