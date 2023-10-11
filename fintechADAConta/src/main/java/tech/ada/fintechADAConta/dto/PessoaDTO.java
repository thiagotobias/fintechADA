package tech.ada.fintechADAConta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {
	private Long id;
    private String nome;
    private String cpf;
    private String cnpj;
}
