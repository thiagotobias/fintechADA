package tech.ada.fintechADAConta.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import tech.ada.fintechADAConta.enums.TipoConta;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaDTO {
	private Long id;
	private PessoaDTO pessoa;
	private String numeroConta;
	private double saldoEmReal;
	private double saldoEmDolar;
	private TipoConta tipo;

}
