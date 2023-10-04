package tech.ada.fintechADAPessoa.service;

import jakarta.validation.Valid;
import tech.ada.fintechADAPessoa.dto.PessoaDTO;

public interface PessoaService {

	PessoaDTO create(@Valid PessoaDTO pessoa);

	PessoaDTO findById(Long id);

}
