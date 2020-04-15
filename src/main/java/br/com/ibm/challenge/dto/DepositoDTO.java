
package br.com.ibm.challenge.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author formiga
 */
public class DepositoDTO extends GenericDTO {
    
    @NotNull(message = "[Conta] não pode ser nula!")
    @Getter
    @Setter
    private ContaDTO contaDTO;
    
    @NotNull(message = "[Valor] não pode ser nulo!")
    @Getter
    @Setter
    private BigDecimal valor;
    
    @NotNull(message = "[Data-Hora] não pode ser nula!")
    @Getter
    @Setter
    private LocalDateTime dataHora;
    
    @NotNull(message = "[Tipo de Depósito] não pode ser nulo!")
    @Getter
    @Setter
    private String tipoDeDeposito;
    
    
    public DepositoDTO() {
        super();
    }
    
    @Builder
    public DepositoDTO(Long id, ContaDTO contaDTO, BigDecimal valor, LocalDateTime dataHora, String tipoDeDeposito) {
        super(id);
        this.contaDTO = contaDTO;
        this.valor = valor;
        this.dataHora = dataHora;
        this.tipoDeDeposito = tipoDeDeposito;
    }
    
}