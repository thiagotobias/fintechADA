package tech.ada.fintechADAConta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import tech.ada.fintechADAConta.enums.TipoConta;

@Entity
@Getter
@Setter
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false)
    private PessoaReplica pessoa;
    
    @Column(unique = true)
    private String numeroConta;

    private double saldoEmReal;
    private double saldoEmDolar;

    @Enumerated(EnumType.STRING)
    private TipoConta tipo;

}
