
package br.com.ibm.challenge.dto;


import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.stereotype.Component;


/**
 *
 * @author formiga
 */
@Component(value = "contaDTO")
public class ContaDTO extends GenericDTO {

    @NotNull(message = "[Agência] não pode ser nula!")
    @Min(value = 0L)
    @Max(value = 9999L, message = "[Número] deve ter no máximo 6 caracteres!")
    private Integer agencia;

    @NotNull(message = "[Número] não pode ser nulo!")
    @Min(value = 0L)
    @Max(value = 999999L, message = "[Número] deve ter no máximo 6 caracteres!")
    private Integer numero;

    @NotNull(message = "[Saldo] não pode ser nulo!")
    @Getter
    @Setter
    private BigDecimal saldo;
    
    @NotNull(message = "[Cliente] não pode ser nulo!")
    @Getter
    @Setter
    private ClienteDTO cliente;
    
}