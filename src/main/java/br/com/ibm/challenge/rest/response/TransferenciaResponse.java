
package br.com.ibm.challenge.rest.response;


import br.com.ibm.challenge.dto.ContaDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author formiga
 */
public class TransferenciaResponse {
    
    @Getter
    @Setter
    private ContaDTO contaOrigem;
    
    @Getter
    @Setter
    private ContaDTO contaDestino;
    
    @Getter
    @Setter
    private BigDecimal valor;
    
    @Getter
    @Setter
    private LocalDateTime dataHora;
    
    
    public TransferenciaResponse() {
        super();
    }
    
    @Builder
    public TransferenciaResponse(ContaDTO contaOrigem, ContaDTO contaDestino, BigDecimal valor, LocalDateTime dataHora) {
    
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.dataHora = dataHora;
    }
    
}