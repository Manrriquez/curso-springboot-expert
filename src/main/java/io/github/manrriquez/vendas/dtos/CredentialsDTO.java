package io.github.manrriquez.vendas.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredentialsDTO {

    private String login;
    private String password;
}
