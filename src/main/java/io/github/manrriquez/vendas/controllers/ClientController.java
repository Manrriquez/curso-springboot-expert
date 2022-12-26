package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.Repositories.ClientRepository;
import io.github.manrriquez.vendas.models.ClientModel;
import io.github.manrriquez.vendas.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {


    @Autowired
    private ClientRepository clientRepository;



    @GetMapping
    public List<ClientModel> getClientAll(ClientModel clientFilter) {

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(clientFilter, matcher);

        return clientRepository.findAll(example);
    }

    @GetMapping("{id}")
    @ResponseBody
    public ClientModel getClientById(@PathVariable Long id) {

        return clientRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientModel saveClient(@RequestBody ClientModel client) {

        return clientRepository.save(client);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {

        clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return client;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClient(@PathVariable Long id, @RequestBody ClientModel client) {

         clientRepository.findById(id)
            .map(clientExistente -> {
                client.setId(clientExistente.getId());
                clientRepository.save(client);
                return clientExistente;
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }
}
