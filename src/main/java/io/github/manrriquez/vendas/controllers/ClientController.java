package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.Repositories.ClientRepository;
import io.github.manrriquez.vendas.models.ClientModel;
import io.github.manrriquez.vendas.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {


    @Autowired
    private ClientRepository clientRepository;



    @GetMapping("/clientes")
    public ResponseEntity getClientAll(ClientModel clientFilter) {

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(clientFilter, matcher);

        List<ClientModel> list = clientRepository.findAll(example);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClientById(@PathVariable Long id) {

        Optional<ClientModel> client = clientRepository.findById(id);
        if(client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("clientes")
    @ResponseBody
    public ResponseEntity saveClient(@RequestBody ClientModel client) {

        ClientModel clientSave = clientRepository.save(client);
        return ResponseEntity.ok(clientSave);
    }


    @DeleteMapping("/clientes/{id}")
    @ResponseBody
    public ResponseEntity deleteClient(@PathVariable Long id) {
        Optional<ClientModel> client = clientRepository.findById(id);

        if(client.isPresent()) {
          clientRepository.delete(client.get());
          return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/clientes/{id}")
    @ResponseBody
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody ClientModel client) {

        return clientRepository.findById(id)
            .map(clientExistente -> {
                client.setId(clientExistente.getId());
                clientRepository.save(client);
                return ResponseEntity.noContent().build();
            }).orElseGet( () -> ResponseEntity.notFound().build()
        );
    }
}
