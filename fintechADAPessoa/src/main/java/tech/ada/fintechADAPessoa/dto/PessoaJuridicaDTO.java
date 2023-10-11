package tech.ada.fintechADAPessoa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaJuridicaDTO extends PessoaDTO {
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String nomeFantasia;
}

