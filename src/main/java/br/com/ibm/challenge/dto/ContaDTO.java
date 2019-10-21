
package br.com.ibm.challenge.dto;


import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Builder;


/**
 *
 * @author formiga
 */
public class ContaDTO extends GenericDTO {

    @NotNull(message = "[Agência] não pode ser nula!")
    @Min(value = 0L)
    @Max(value = 9999L, message = "[Número] deve ter no máximo 6 caracteres!")
    @Getter
    @Setter
    private Integer agencia;

    @NotNull(message = "[Número] não pode ser nulo!")
    @Min(value = 0L)
    @Max(value = 999999L, message = "[Número] deve ter no máximo 6 caracteres!")
    @Getter
    @Setter
    private Integer numero;

    @NotNull(message = "[Saldo] não pode ser nulo!")
    @Getter
    @Setter
    private BigDecimal saldo;
    
    @NotNull(message = "[Cliente] não pode ser nulo!")
    @Getter
    @Setter
    private ClienteDTO clienteDTO;
    
    
    public ContaDTO() {
        super();
    }

    @Builder
    public ContaDTO(Long id, Integer agencia, Integer numero, BigDecimal saldo, ClienteDTO clienteDTO) {
        super(id);
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.clienteDTO = clienteDTO;
    }
    
}