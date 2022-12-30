package io.github.manrriquez.vendas.services;


import io.github.manrriquez.vendas.Enums.StatusDemand;
import io.github.manrriquez.vendas.dtos.DemandDTO;
import io.github.manrriquez.vendas.models.DemandModel;

import java.util.Optional;

public interface DemandService {
    DemandModel save(DemandDTO dto);

    Optional<DemandModel> getFullOrder(Long id);

    void updateStatus(Long id, StatusDemand statusDemand);
}
