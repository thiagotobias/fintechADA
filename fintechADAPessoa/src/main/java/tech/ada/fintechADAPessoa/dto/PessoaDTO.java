package tech.ada.fintechADAPessoa.dto;

import lombok.Getter;
import lombok.Setter;
import tech.ada.fintechADAPessoa.enums.TipoPessoa;

@Getter
@Setter
public abstract class PessoaDTO {
	private Long id;
    private String nome;
    private String email;
    private String site;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;
    private String estado;
    private TipoPessoa tipoPessoa;
}
