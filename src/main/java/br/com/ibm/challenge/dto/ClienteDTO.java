
package br.com.ibm.challenge.dto;


import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;


/**
 *
 * @author formiga
 */
@Component(value = "clienteDTO")
public class ClienteDTO extends GenericDTO {
    
    @NotNull(message = "[Nome] não pode ser nulo!")
    @Getter
    @Setter
    private String nome;
    
    @NotNull
    @Length(min = 1, max = 60, message = "[CPF] deve ter entre {min} e {max} caracteres!")
    @Getter
    @Setter
    private String email;
    
}