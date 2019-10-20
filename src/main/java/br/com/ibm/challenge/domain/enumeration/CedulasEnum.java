
package br.com.ibm.challenge.domain.enumeration;


import lombok.Getter;


/**
 *
 * @author formiga
 */
public enum CedulasEnum {
    
    UM(1),
    DOIS(2),
    CINCO(5),
    DEZ(10),
    VINTE(20),
    CINQUENTA(50),
    CEM(100);
    
    
    @Getter
    private final Integer VALOR;


    CedulasEnum(Integer valor) {

        this.VALOR = valor;
    }

}