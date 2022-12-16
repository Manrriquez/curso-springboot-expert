package io.github.manrriquez.vendas.Repositories;


import io.github.manrriquez.vendas.models.ClientModel;
import io.github.manrriquez.vendas.models.DemandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<DemandModel, Long> {

    List<DemandModel> findByClient(ClientModel client);
}
