package tech.ada.fintechADAPessoa.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFisicaDTO extends PessoaDTO {
    private String cpf;
    private String rg;
    private LocalDateTime dataRg;
    private String orgaoRg;
    private String sexo;
    private String raca;
    private String naturalidade;
    private String nacionalidade;
    private String nomePai;
    private String nomeMae;

}

