
package br.com.ibm.challenge.exceptions;


/**
 *
 * @author formiga
 */
public class SaqueException extends Exception {
    
    public SaqueException() {
        super("Saque Exception!");
    }
    
    public SaqueException(String mensagem) {
        
        super(mensagem);
    }
    
}