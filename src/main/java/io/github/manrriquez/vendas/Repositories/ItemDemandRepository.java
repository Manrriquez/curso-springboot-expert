package io.github.manrriquez.vendas.Repositories;

import io.github.manrriquez.vendas.models.ItemDemandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemDemandRepository extends JpaRepository<ItemDemandModel, Long> {
}
