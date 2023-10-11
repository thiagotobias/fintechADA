package tech.ada.fintechADAConta.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import tech.ada.fintechADAConta.dto.ContaDTO;
import tech.ada.fintechADAConta.dto.PessoaDTO;
import tech.ada.fintechADAConta.dto.TransacaoDTO;
import tech.ada.fintechADAConta.enums.TipoCambio;
import tech.ada.fintechADAConta.enums.TipoConta;
import tech.ada.fintechADAConta.enums.TipoTransacao;
import tech.ada.fintechADAConta.exception.ContaException;
import tech.ada.fintechADAConta.model.Conta;
import tech.ada.fintechADAConta.model.PessoaReplica;
import tech.ada.fintechADAConta.model.Transacao;
import tech.ada.fintechADAConta.repository.ContaRepository;
import tech.ada.fintechADAConta.repository.PessoaReplicaRepository;
import tech.ada.fintechADAConta.repository.TransacaoRepository;
import tech.ada.fintechADAConta.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private PessoaReplicaRepository pessoaRepository;

	@Autowired
	private TransacaoRepository transacaoRepository;

	private static final int NUM_DIGITOS = 10; // Defina o número de dígitos desejado para o número da conta

	@Override
	public ContaDTO create(Long idPessoa, TipoConta tipoConta) {
		PessoaReplica pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com o ID: " + idPessoa));
		Conta conta = new Conta();
		String numeroConta = generateNumeroConta();

		conta.setNumeroConta(numeroConta);
		conta.setSaldoEmReal(0.0);
		conta.setSaldoEmDolar(0.0);
		conta.setTipo(tipoConta);
		conta.setPessoa(pessoa);

		conta = contaRepository.save(conta);

		ContaDTO contaDTO = new ContaDTO();
		contaDTO.setId(conta.getId());
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setCnpj(conta.getPessoa().getCnpj());
		pessoaDTO.setCpf(conta.getPessoa().getCpf());
		pessoaDTO.setId(conta.getPessoa().getId());
		pessoaDTO.setNome(conta.getPessoa().getNome());
		contaDTO.setPessoa(pessoaDTO);
		contaDTO.setNumeroConta(conta.getNumeroConta());
		contaDTO.setSaldoEmReal(conta.getSaldoEmReal());
		contaDTO.setSaldoEmDolar(conta.getSaldoEmDolar());
		contaDTO.setTipo(conta.getTipo());

		return contaDTO;
	}

	@Override
	public ContaDTO findById(Long id) {
		Conta conta = contaRepository.findById(id).orElseThrow(() ->
				new ContaException("Conta não encontrada com o ID: " + id)
		);
		return convertToContaDTO(conta);
	}

	@Override
	public List<ContaDTO> findAllByPessoaId(Long idPessoa) {
		Optional<PessoaReplica> pessoa = pessoaRepository.findById(idPessoa);
		
		if (pessoa.get().getContas().isEmpty()) {
			throw new ContaException("Conta não encontrada com o ID Pessoa: " + idPessoa);
		}
		
		List<ContaDTO> contas = new ArrayList<>(pessoa.get().getContas().size());
		PessoaDTO pessoaDto = new PessoaDTO();
		pessoaDto.setId(pessoa.get().getId());
		pessoaDto.setNome(pessoa.get().getNome());
		pessoaDto.setCpf(pessoa.get().getCpf());
		pessoaDto.setCnpj(pessoa.get().getCnpj());
		for (Conta conta : pessoa.get().getContas()) {
			ContaDTO contaDto = new ContaDTO();
			contaDto.setId(conta.getId());
			contaDto.setNumeroConta(conta.getNumeroConta());
			
			contaDto.setPessoa(pessoaDto);
			contaDto.setSaldoEmDolar(conta.getSaldoEmDolar());
			contaDto.setSaldoEmReal(conta.getSaldoEmReal());
			contaDto.setTipo(conta.getTipo());
			contas.add(contaDto);
		}

		return contas;
	}

	@Override
	public TransacaoDTO realizarTransacao(TransacaoDTO transacaoDTO) {
		if (transacaoDTO.getTipo().equals(TipoTransacao.TRANSFERENCIA)) {
			return realizarTransferencia(transacaoDTO);
		} else if (transacaoDTO.getTipo().equals(TipoTransacao.EMPRESTIMO)) {
			return realizarEmprestimo(transacaoDTO);
		} else if (transacaoDTO.getTipo().equals(TipoTransacao.CAMBIO)) {
			return realizarCambio(transacaoDTO);
		} else {
			throw new ContaException("Tipo de transação inválido: " + transacaoDTO.getTipo());
		}
	}

	private TransacaoDTO realizarTransferencia(TransacaoDTO transacaoDTO) {
		Conta contaOrigem = contaRepository.findById(transacaoDTO.getContaOrigemId()).orElseThrow(() ->
				new ContaException("Conta de origem não encontrada.")
		);
		Conta contaDestino = contaRepository.findById(transacaoDTO.getContaDestinoId()).orElseThrow(() ->
				new ContaException("Conta de destino não encontrada.")
		);
		double valorTransferencia = transacaoDTO.getValor();
		if (contaOrigem.getSaldoEmReal() < valorTransferencia) {
			throw new ContaException("Saldo insuficiente na conta de origem.");
		}

		contaOrigem.setSaldoEmReal(contaOrigem.getSaldoEmReal() - valorTransferencia);
		contaDestino.setSaldoEmReal(contaDestino.getSaldoEmReal() + valorTransferencia);

		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);

		Transacao transacao = new Transacao();
		transacao.setContaOrigem(contaOrigem);
		transacao.setContaDestino(contaDestino);
		transacao.setValor(valorTransferencia);
		transacao.setTipo(TipoTransacao.TRANSFERENCIA);
		transacao.setDataHora(LocalDateTime.now());
		transacaoRepository.save(transacao);

		return convertToTransacaoDTO(transacao);
	}

	private TransacaoDTO realizarEmprestimo(TransacaoDTO transacaoDTO) {
		Conta contaDestino = contaRepository.findById(transacaoDTO.getContaDestinoId()).orElseThrow(() ->
				new ContaException("Conta de destino não encontrada.")
		);
		double valorEmprestimo = transacaoDTO.getValor();

		contaDestino.setSaldoEmReal(contaDestino.getSaldoEmReal() + valorEmprestimo);

		contaRepository.save(contaDestino);

		Transacao transacao = new Transacao();
		transacao.setContaDestino(contaDestino);
		transacao.setValor(valorEmprestimo);
		transacao.setTipo(TipoTransacao.EMPRESTIMO);
		transacao.setDataHora(LocalDateTime.now());
		transacaoRepository.save(transacao);

		return convertToTransacaoDTO(transacao);
	}

	private TransacaoDTO realizarCambio(TransacaoDTO transacaoDTO) {
		Conta contaOrigem = contaRepository.findById(transacaoDTO.getContaOrigemId()).orElseThrow(() ->
				new ContaException("Conta de origem não encontrada.")
		);

		double valor = transacaoDTO.getValor();
		double taxaCambio = 5.0;

		if (transacaoDTO.getTipoCambio().equals(TipoCambio.COMPRA_DOLAR)) {

			double valorEmReal = valor * taxaCambio;

			if (contaOrigem.getSaldoEmReal() <= valorEmReal) {
				throw new ContaException("Saldo insuficiente na conta de origem.");
			}

			contaOrigem.setSaldoEmReal(contaOrigem.getSaldoEmReal() - valorEmReal);
			contaOrigem.setSaldoEmDolar(contaOrigem.getSaldoEmDolar() + valor);

		} else if (transacaoDTO.getTipoCambio().equals(TipoCambio.VENDA_DOLAR)) {

			if (contaOrigem.getSaldoEmDolar() <= valor) {
				throw new ContaException("Saldo insuficiente na conta de origem.");
			}

			double valorEmReal = valor / taxaCambio;
			contaOrigem.setSaldoEmReal(contaOrigem.getSaldoEmReal() + valorEmReal);
			contaOrigem.setSaldoEmDolar(contaOrigem.getSaldoEmDolar() - valor);

		} else if (transacaoDTO.getTipoCambio().equals(TipoCambio.COMPRA_REAL)) {

			double valorEmDolar = valor / taxaCambio;

			if (contaOrigem.getSaldoEmDolar() <= valorEmDolar) {
				throw new ContaException("Saldo insuficiente na conta de origem.");
			}

			contaOrigem.setSaldoEmReal(contaOrigem.getSaldoEmReal() + valor);
			contaOrigem.setSaldoEmDolar(contaOrigem.getSaldoEmDolar() - valorEmDolar);

		} else if (transacaoDTO.getTipoCambio().equals(TipoCambio.VENDA_REAL)) {

			if (contaOrigem.getSaldoEmReal() < valor) {
				throw new ContaException("Saldo insuficiente na conta de origem.");
			}

			double valorEmDolar = valor / taxaCambio;
			contaOrigem.setSaldoEmReal(contaOrigem.getSaldoEmReal() - valor);
			contaOrigem.setSaldoEmDolar(contaOrigem.getSaldoEmDolar() + valorEmDolar);

		} else {
			throw new ContaException("Tipo de câmbio inválido: " + transacaoDTO.getTipoCambio());
		}

		contaRepository.save(contaOrigem);

		Transacao transacao = new Transacao();
		transacao.setContaOrigem(contaOrigem);
		transacao.setValor(transacaoDTO.getValor());
		transacao.setTipo(TipoTransacao.CAMBIO);
		transacao.setDataHora(LocalDateTime.now());
		transacaoRepository.save(transacao);

		return convertToTransacaoDTO(transacao);
	}

	private static String generateNumeroConta() {
		Random random = new Random();
		StringBuilder numeroConta = new StringBuilder();

		Set<String> numerosContaGerados = new HashSet<>();

		while (true) {
			// Gere uma sequência de dígitos aleatórios com o número de dígitos especificado
			for (int i = 0; i < NUM_DIGITOS; i++) {
				int digito = random.nextInt(10); // Gere um dígito aleatório de 0 a 9
				numeroConta.append(digito);
			}

			String numeroContaGerado = numeroConta.toString();

			// Verifique se o número de conta gerado já existe
			if (!numerosContaGerados.contains(numeroContaGerado)) {
				numerosContaGerados.add(numeroContaGerado);
				return numeroContaGerado;
			}

			// Limpe o StringBuilder para gerar um novo número de conta
			numeroConta.setLength(0);
		}
	}

	private ContaDTO convertToContaDTO(Conta conta) {
		ContaDTO contaDTO = new ContaDTO();
		contaDTO.setId(conta.getId());
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setId(conta.getPessoa().getId());
		pessoaDTO.setNome(conta.getPessoa().getNome());
		pessoaDTO.setCnpj(conta.getPessoa().getCnpj());
		pessoaDTO.setCpf(conta.getPessoa().getCpf());
		contaDTO.setPessoa(pessoaDTO);
		contaDTO.setNumeroConta(conta.getNumeroConta());
		contaDTO.setSaldoEmReal(conta.getSaldoEmReal());
		contaDTO.setSaldoEmDolar(conta.getSaldoEmDolar());
		contaDTO.setTipo(conta.getTipo());
		return contaDTO;
	}
	
	private TransacaoDTO convertToTransacaoDTO(Transacao transacao) {
		TransacaoDTO transacaoDTO = new TransacaoDTO();
		transacaoDTO.setId(transacao.getId());
		if (!transacao.getTipo().equals(TipoTransacao.EMPRESTIMO)) {
			transacaoDTO.setContaOrigemId(transacao.getContaOrigem().getId());
		}
		if (!transacao.getTipo().equals(TipoTransacao.CAMBIO)) {
			transacaoDTO.setContaDestinoId(transacao.getContaDestino().getId());
		}
		transacaoDTO.setValor(transacao.getValor());
		transacaoDTO.setTipo(transacao.getTipo());
		transacaoDTO.setDataHora(transacao.getDataHora());
		if (transacao.getTipo().equals(TipoTransacao.CAMBIO)){
			transacaoDTO.setTipoCambio(transacao.getTipoCambio());
		}
		return transacaoDTO;
	}
}
