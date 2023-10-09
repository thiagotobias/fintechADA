package tech.ada.fintechADAConta.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

import tech.ada.fintechADAConta.dto.ContaDTO;
import tech.ada.fintechADAConta.dto.TransacaoDTO;
import tech.ada.fintechADAConta.enums.TipoConta;
import tech.ada.fintechADAConta.exception.ContaException;
import tech.ada.fintechADAConta.service.ContaService;

@RestController
@Validated
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaDTO> create(@Valid @RequestBody ContaDTO contaDTO) {
        Long idPessoa = contaDTO.getIdPessoa();
        TipoConta tipoConta = contaDTO.getTipo();

        ContaDTO createdConta = contaService.create(idPessoa, tipoConta);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdConta.getId())
                .toUri();

        return ResponseEntity.created(uri).body(createdConta);
    }

    @GetMapping("by-conta/{id}")
    public ResponseEntity<?> getDetalhes(@PathVariable(name = "id") Long id) {
        try {
            ContaDTO contaDTO = contaService.findById(id);
            return ResponseEntity.ok(contaDTO);
        } catch (ContaException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getLocalizedMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/by-pessoa/{idPessoa}")
    public ResponseEntity<?> getContasByPessoa(@PathVariable(name = "idPessoa") Long idPessoa) {
        try {
            List<ContaDTO> contas = contaService.findAllByPessoaId(idPessoa);
            return ResponseEntity.ok(contas);
        } catch (ContaException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getLocalizedMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
        }
    }

    @PostMapping("/transacao")
    public ResponseEntity<?> realizarTransacao(@Valid @RequestBody TransacaoDTO transacaoDTO) {
        try {
            TransacaoDTO transacaoCriada = contaService.realizarTransacao(transacaoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transacaoCriada.getId())
                .toUri();

        return ResponseEntity.created(uri).body(transacaoCriada);
        } catch (ContaException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getLocalizedMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
        }
    }
}
