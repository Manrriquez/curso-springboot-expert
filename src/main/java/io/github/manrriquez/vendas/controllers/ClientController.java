package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.Repositories.ClientRepository;
import io.github.manrriquez.vendas.models.ClientModel;
import io.github.manrriquez.vendas.services.ServiceImpl.UserDetailsServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClientController {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @GetMapping
    public List<ClientModel> getClientAll(ClientModel clientFilter) {

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(clientFilter, matcher);

        return clientRepository.findAll(example);
    }

    @GetMapping("{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado")
    })
    public ClientModel getClientById(@PathVariable @ApiParam("Id do cliente") Long id) {

        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salvar cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public ClientModel saveClient(@RequestBody @Valid ClientModel client) {
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
    public void updateClient(@PathVariable Long id, @Valid @RequestBody ClientModel client) {

        clientRepository.findById(id)
                .map(clientExistente -> {
                    client.setId(clientExistente.getId());
                    clientRepository.save(client);
                    return clientExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }
}
