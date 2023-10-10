package tech.ada.fintechADAPessoa.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import tech.ada.fintechADAPessoa.dto.PessoaDTO;
import tech.ada.fintechADAPessoa.service.PessoaService;

@RestController
@Validated
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService service;

	@PostMapping
	public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO pessoa) {
		PessoaDTO createdPessoa = service.create(pessoa);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdPessoa.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> get(@PathVariable(name = "id") Long id) {
    	PessoaDTO pessoa = service.findById(id);		
        return ResponseEntity.ok(pessoa);
    }
}
