package tech.ada.fintechADAConta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.ada.fintechADAConta.enums.TipoCambio;
import tech.ada.fintechADAConta.enums.TipoTransacao;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestino;

    private double valor;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    @Enumerated(EnumType.STRING)
    private TipoCambio tipoCambio;

    private LocalDateTime dataHora;
}
