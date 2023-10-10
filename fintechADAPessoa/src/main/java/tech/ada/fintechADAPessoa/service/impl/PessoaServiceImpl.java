package tech.ada.fintechADAPessoa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import tech.ada.fintechADAPessoa.dto.PessoaDTO;
import tech.ada.fintechADAPessoa.dto.parse.PessoaParser;
import tech.ada.fintechADAPessoa.exception.PessoaException;
import tech.ada.fintechADAPessoa.repository.PessoaRepository;
import tech.ada.fintechADAPessoa.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository repository;

	@Override
	public PessoaDTO create(@Valid PessoaDTO pessoa) {
		return PessoaParser.toPessoaDTO(repository.save(PessoaParser.toPessoaEntity(pessoa)));
	}

	@Override
	public PessoaDTO findById(Long id) {
		return PessoaParser.toPessoaDTO(
				repository.findById(id).orElseThrow(() -> new PessoaException("Não foi possivel buscar a pessoa.")));
	}

}
