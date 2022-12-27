package io.github.manrriquez.vendas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandDTO {


    private Long client;
    private BigDecimal amount;
    private List<ItemDemandDTO> items;
}
