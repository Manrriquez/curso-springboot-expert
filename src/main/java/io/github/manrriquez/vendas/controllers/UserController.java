package io.github.manrriquez.vendas.controllers;


import io.github.manrriquez.vendas.dtos.CredentialsDTO;
import io.github.manrriquez.vendas.dtos.TokenDTO;
import io.github.manrriquez.vendas.exceptions.PasswordInvalidException;
import io.github.manrriquez.vendas.models.UserModel;
import io.github.manrriquez.vendas.services.JwtService;
import io.github.manrriquez.vendas.services.ServiceImpl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel saveUser(@RequestBody @Valid UserModel user) {
        String passwordEncrypted = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypted);

        return userDetailsServiceImpl.saveUser(user);
    }

    @PostMapping("/auth")
    public TokenDTO authenticated(@RequestBody CredentialsDTO credentials) {

        try {
            UserModel user = UserModel.builder()
                    .login(credentials.getLogin())
                    .password(credentials.getPassword()).build();
            UserDetails userAuthenticated = userDetailsServiceImpl.authenticated(user);
            String token = jwtService.gerarToken(user);
            return new TokenDTO(user.getLogin(), token);

        } catch (UsernameNotFoundException | PasswordInvalidException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
