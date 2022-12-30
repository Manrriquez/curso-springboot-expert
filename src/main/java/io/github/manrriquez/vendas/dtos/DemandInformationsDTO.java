package io.github.manrriquez.vendas.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemandInformationsDTO {

    private Long code;
    private String cpf;
    private String nameClient;
    private BigDecimal amount;
    private String dateDemand;
    private String status;
    private List<DemandItemInformationDTO> items;
}
