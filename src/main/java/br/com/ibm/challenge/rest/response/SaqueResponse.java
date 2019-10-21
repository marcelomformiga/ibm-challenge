
package br.com.ibm.challenge.rest.response;


import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author formiga
 */
public class SaqueResponse {
    
    @Getter
    @Setter
    private String cedula;
    
    @Getter
    @Setter
    private Integer valor;
    
    @Getter
    @Setter
    private Integer quantidade;
    
    @Getter
    @Setter
    private Long valorTotalPorCedula;
    
    @Getter
    @Setter
    private Long valorTotalDoSaque;
    
    @Getter
    @Setter
    private Long contaId;
    
    @Getter
    @Setter
    private BigDecimal saldoDaConta;
    
    
    public SaqueResponse() {
        super();
    }
    
    @Builder
    public SaqueResponse(String cedula, Integer quantidade, Integer valor, Long valorTotalPorCedula, Long contaId, BigDecimal saldoDaConta) {
        this.cedula = cedula;
        this.quantidade = quantidade;
        this.valor = valor;
        this.valorTotalPorCedula = valorTotalPorCedula;
        this.contaId = contaId;
        this.saldoDaConta = saldoDaConta;
    }
    
}