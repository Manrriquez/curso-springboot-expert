package io.github.manrriquez.vendas.dtos;

import io.github.manrriquez.vendas.annotations.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandDTO {


    @NotNull(message = "{FIELD.CODE-CLIENT.REQUIRED}")
    private Long client;

    @NotNull(message = "{FIELD.AMOUNT-DEMAND.REQUIRED}")
    private BigDecimal amount;

    @NotEmptyList(message = "{FIELD.ITEMS-DEMAND.REQUIRED}")
    private List<ItemDemandDTO> items;
}
