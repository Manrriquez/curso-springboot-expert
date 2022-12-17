package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.Repositories.ClientRepository;
import io.github.manrriquez.vendas.models.ClientModel;
import io.github.manrriquez.vendas.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {


    @Autowired
    private ClientRepository clientRepository;

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


    @DeleteMapping("clientes/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        Optional<ClientModel> client = clientRepository.findById(id);

        if(client.isPresent()) {
          clientRepository.delete(client.get());
          return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
