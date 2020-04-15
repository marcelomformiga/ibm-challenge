
package br.com.ibm.challenge.exceptions;


/**
 *
 * @author formiga
 */
public class DepositoException extends Exception {
    
    public DepositoException() {
        super("Deposito Exception!");
    }
    
    public DepositoException(String mensagem) {
        
        super(mensagem);
    }
    
}