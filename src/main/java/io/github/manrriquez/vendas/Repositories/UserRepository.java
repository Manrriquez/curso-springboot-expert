package io.github.manrriquez.vendas.Repositories;

import io.github.manrriquez.vendas.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByLogin(String login);
}
