package tech.ada.fintechADAConta.dto;

import lombok.Getter;
import lombok.Setter;
import tech.ada.fintechADAConta.enums.TipoCambio;
import tech.ada.fintechADAConta.enums.TipoTransacao;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransacaoDTO {
    private Long id;
    private Long contaOrigemId;
    private Long contaDestinoId;
    private double valor;
    private TipoTransacao tipo;
    private TipoCambio tipoCambio;
    private LocalDateTime dataHora;
}
