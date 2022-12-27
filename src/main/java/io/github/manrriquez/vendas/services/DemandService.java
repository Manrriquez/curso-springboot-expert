package io.github.manrriquez.vendas.services;


import io.github.manrriquez.vendas.dtos.DemandDTO;
import io.github.manrriquez.vendas.models.DemandModel;

public interface DemandService {
    DemandModel save(DemandDTO dto);
}
