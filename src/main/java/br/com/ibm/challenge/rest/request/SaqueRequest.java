
package br.com.ibm.challenge.rest.request;


import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author formiga
 */
public class SaqueRequest {
    
    @NotNull(message = "[Agência] não pode ser nula!")
    @Min(value = 0L)
    @Max(value = 9999L, message = "[Número] deve ter no máximo 6 caracteres!")
    @Getter
    @Setter
    private Integer agencia;
    
    @NotNull(message = "[Agência] não pode ser nula!")
    @Min(value = 0L)
    @Max(value = 999999L, message = "[Número] deve ter no máximo 6 caracteres!")
    @Getter
    @Setter
    private Integer numeroConta;
    
    @Getter
    @Setter
    private BigDecimal valor;
    
}