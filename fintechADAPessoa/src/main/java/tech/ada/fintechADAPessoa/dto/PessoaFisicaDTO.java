package tech.ada.fintechADAPessoa.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFisicaDTO extends PessoaDTO {
    private String cpf;
    private String rg;
    private LocalDate dataRg;
    private String orgaoRg;
    private String sexo;
    private String raca;
    private String naturalidade;
    private String nacionalidade;
    private String nomePai;
    private String nomeMae;

}

