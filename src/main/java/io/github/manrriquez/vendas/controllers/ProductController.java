package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.Repositories.ProductRepository;
import io.github.manrriquez.vendas.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProductController {

    @Autowired
    ProductRepository productRepository;


    @GetMapping
    public List<ProductModel> getProductAll(ProductModel productFilter) {

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(productFilter, matcher);

        return productRepository.findAll(example);
    }

    @GetMapping("{id}")
    @ResponseBody
    public ProductModel getProductById(@PathVariable Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModel saveProduct(@RequestBody @Valid ProductModel product) {

        return productRepository.save(product);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {

        productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return product;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @Valid @RequestBody ProductModel product) {

        productRepository.findById(id)
                .map(productExistente -> {
                    product.setId(productExistente.getId());
                    productRepository.save(product);
                    return productExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }
}
