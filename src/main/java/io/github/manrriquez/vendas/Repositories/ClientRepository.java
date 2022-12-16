package io.github.manrriquez.vendas.Repositories;


import io.github.manrriquez.vendas.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    @Query(value = "select c from ClientModel c where c.name like :name ")
    List<ClientModel> findByName(@Param("name") String name);

    @Query(value = "select c from ClientModel c left join fetch c.demands where c.id = :id")
    ClientModel findByClientFetchDemand(@Param("id") Long id);

}
