package io.github.manrriquez.vendas.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "TB_USER")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @NotEmpty(message = "{FIELD.LOGIN.REQUIRED}")
    private String login;

    @Column
    @NotEmpty(message = "{FIELD.PASSWORD.REQUIRED}")
    private String password;

    @Column
    private boolean admin;

}
