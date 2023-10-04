package tech.ada.fintechADAPessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.ada.fintechADAPessoa.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
