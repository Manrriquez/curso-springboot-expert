package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.models.UserModel;
import io.github.manrriquez.vendas.services.ServiceImpl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel saveUser(RequestBody @Valid UserModel user) {

        String passwordEncrypt = passwordEncoder.encode(user().getPassword());
        user().setPassword(passwordEncrypt);
        return userDetailsServiceImpl.saveUser(user);
    }
}
