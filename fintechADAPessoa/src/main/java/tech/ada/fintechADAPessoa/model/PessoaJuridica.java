package tech.ada.fintechADAPessoa.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("PJ")
public class PessoaJuridica extends Pessoa {
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String nomeFantasia;
}

