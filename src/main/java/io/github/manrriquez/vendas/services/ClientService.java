package io.github.manrriquez.vendas.services;


import io.github.manrriquez.vendas.Repositories.ClientRepository;
import io.github.manrriquez.vendas.models.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClientService {

    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientModel save(ClientModel clientModel) {

        return clientRepository.save(clientModel);
    }

    public Page<ClientModel> findAll(Pageable pageable) {

        return clientRepository.findAll(pageable);
    }

    @Transactional
    public void delete(ClientModel clientModel) {
        clientRepository.delete(clientModel);
    }
}
