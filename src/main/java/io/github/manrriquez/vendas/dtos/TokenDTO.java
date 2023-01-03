package io.github.manrriquez.vendas.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {

    private String login;
    private String token;
}
