package tech.ada.fintechADAPessoa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import tech.ada.fintechADAPessoa.client.ContaClient;
import tech.ada.fintechADAPessoa.client.PessoaReplicaDTO;
import tech.ada.fintechADAPessoa.dto.PessoaDTO;
import tech.ada.fintechADAPessoa.dto.PessoaFisicaDTO;
import tech.ada.fintechADAPessoa.dto.PessoaJuridicaDTO;
import tech.ada.fintechADAPessoa.dto.parse.PessoaParser;
import tech.ada.fintechADAPessoa.exception.PessoaException;
import tech.ada.fintechADAPessoa.repository.PessoaRepository;
import tech.ada.fintechADAPessoa.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private ContaClient contaClient;

	@Override
	public PessoaDTO create(@Valid PessoaDTO pessoa) {
		PessoaDTO pessoaDTO;
		PessoaReplicaDTO pessoaReplica;
		
		if(pessoa instanceof PessoaFisicaDTO ) {
			PessoaFisicaDTO pessoaFisica = (PessoaFisicaDTO) PessoaParser.toPessoaDTO(repository.save(PessoaParser.toPessoaEntity(pessoa)));
			pessoaDTO = pessoaFisica;
			pessoaReplica = new PessoaReplicaDTO(pessoaFisica.getId(), pessoaFisica.getNome(), pessoaFisica.getCpf(), null);
			
		} else {
			PessoaJuridicaDTO pessoaJurica = (PessoaJuridicaDTO) PessoaParser.toPessoaDTO(repository.save(PessoaParser.toPessoaEntity(pessoa)));
			pessoaDTO = pessoaJurica;
			pessoaReplica = new PessoaReplicaDTO(pessoaJurica.getId(), pessoaJurica.getNome(), null, pessoaJurica.getCnpj() );
		}
		contaClient.create(pessoaReplica);
		return pessoaDTO;
	}

	@Override
	public PessoaDTO findById(Long id) {
		return PessoaParser.toPessoaDTO(
				repository.findById(id).orElseThrow(() -> new PessoaException("Não foi possivel buscar a pessoa.")));
	}

}
