package tech.ada.fintechADAConta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.ada.fintechADAConta.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
