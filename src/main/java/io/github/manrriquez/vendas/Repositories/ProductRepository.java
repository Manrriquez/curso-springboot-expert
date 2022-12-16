package io.github.manrriquez.vendas.Repositories;

import io.github.manrriquez.vendas.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
