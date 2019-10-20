
package br.com.ibm.challenge.dto;


import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author formiga
 */
public class GenericDTO {
    
    @Getter
    @Setter
    private Long id;
    
    
    public GenericDTO(Long id) {
        this.id = id;
    }
    
}