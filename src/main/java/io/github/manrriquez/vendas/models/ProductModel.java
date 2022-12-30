package io.github.manrriquez.vendas.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PRODUCT")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    @NotEmpty(message = "{FIELD.DESCRIPTION.REQUIRED}")
    private String description;

    @NotNull(message = "{FIELD.PRICE.REQUIRED}")
    private BigDecimal amount;
}
