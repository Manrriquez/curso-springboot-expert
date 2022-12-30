package io.github.manrriquez.vendas.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemandItemInformationDTO {

    private String descriptionProduct;
    private BigDecimal amountUnity;

    private Long amount;
}
