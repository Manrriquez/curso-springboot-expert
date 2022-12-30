package io.github.manrriquez.vendas.Repositories;


import io.github.manrriquez.vendas.models.ClientModel;
import io.github.manrriquez.vendas.models.DemandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandRepository extends JpaRepository<DemandModel, Long> {

    List<DemandModel> findByClient(ClientModel client);

    @Query("select p from DemandModel p left join fetch p.items where p.id = :id")
    Optional<DemandModel> findByFetchItems(@Param("id") Long id);
}
