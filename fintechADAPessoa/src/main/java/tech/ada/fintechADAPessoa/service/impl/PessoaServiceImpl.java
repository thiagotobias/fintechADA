package tech.ada.fintechADAPessoa.service.impl;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import tech.ada.fintechADAPessoa.dto.PessoaDTO;
import tech.ada.fintechADAPessoa.dto.parse.PessoaParser;
import tech.ada.fintechADAPessoa.repository.PessoaRepository;
import tech.ada.fintechADAPessoa.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {
	
	private PessoaRepository repository;

	@Override
	public PessoaDTO create(@Valid PessoaDTO pessoa) {
		return PessoaParser
                .toPessoaDTO(repository.save(PessoaParser.toPessoaEntity(pessoa)));
	}

	@Override
	public PessoaDTO findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
