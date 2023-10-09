package tech.ada.fintechADAConta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.ada.fintechADAConta.enums.TipoCambio;
import tech.ada.fintechADAConta.enums.TipoConta;
import tech.ada.fintechADAConta.enums.TipoTransacao;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idPessoa;

    @Column(unique = true)
    private String numeroConta;

    private double saldoEmReal;
    private double saldoEmDolar;

    @Enumerated(EnumType.STRING)
    private TipoConta tipo;

}
