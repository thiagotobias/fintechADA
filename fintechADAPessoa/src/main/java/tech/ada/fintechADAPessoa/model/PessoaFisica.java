package tech.ada.fintechADAPessoa.model;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("PF")
public class PessoaFisica extends Pessoa {
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

