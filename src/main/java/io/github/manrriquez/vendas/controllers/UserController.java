package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.dtos.CredentialsDTO;
import io.github.manrriquez.vendas.dtos.TokenDTO;
import io.github.manrriquez.vendas.models.ClientModel;
import io.github.manrriquez.vendas.models.UserModel;
import io.github.manrriquez.vendas.services.ServiceImpl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel saveUser(@RequestBody @Valid UserModel user) {
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);

        return userDetailsServiceImpl.saveUser(user);
    }

    @PostMapping("/auth")
    public TokenDTO authenticated(@RequestBody CredentialsDTO credentials) {

        try {
            UserDetails userAuthenticated = userDetailsServiceImpl.authenticated(
                    User.builder()
                            .username(credentials.getLogin())
                            .password(credentials.getPassword().build());
            );
        }
    }
}
