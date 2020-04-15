
package br.com.ibm.challenge.exceptions;


/**
 *
 * @author formiga
 */
public class TransferenciaException extends Exception {
    
    public TransferenciaException() {
        super("Transferencia Exception!");
    }
    
    public TransferenciaException(String mensagem) {
        
        super(mensagem);
    }
    
}