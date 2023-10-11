package tech.ada.fintechADAConta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.ada.fintechADAConta.model.PessoaReplica;
import tech.ada.fintechADAConta.repository.PessoaReplicaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaReplicaController {
	
	@Autowired
	private PessoaReplicaRepository pessoaReplicaRepository;
	
	@PostMapping()
	public PessoaReplica create(@RequestBody PessoaReplica pessoaReplica) {
		return pessoaReplicaRepository.save(pessoaReplica);
	}
}
