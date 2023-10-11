package tech.ada.fintechADAConta.service;

import tech.ada.fintechADAConta.dto.ContaDTO;
import tech.ada.fintechADAConta.dto.TransacaoDTO;
import tech.ada.fintechADAConta.enums.TipoConta;

import java.util.List;

public interface ContaService {
	ContaDTO create(Long idPessoa, TipoConta tipoConta);

	ContaDTO findById(Long id);

	List<ContaDTO> findAllByPessoaId(Long idPessoa);

	TransacaoDTO realizarTransacao(TransacaoDTO transacaoDTO);
}
