
package br.com.ibm.challenge.service;


import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.dto.ContaDTO;
import br.com.ibm.challenge.dto.DepositoDTO;
import br.com.ibm.challenge.exceptions.DepositoException;
import br.com.ibm.challenge.exceptions.SaqueException;
import br.com.ibm.challenge.exceptions.TransferenciaException;
import br.com.ibm.challenge.rest.request.DepositoRequest;
import br.com.ibm.challenge.rest.request.SaqueRequest;
import br.com.ibm.challenge.rest.request.TransferenciaRequest;
import br.com.ibm.challenge.rest.response.SaqueResponse;
import br.com.ibm.challenge.rest.response.TransferenciaResponse;
import java.util.List;


/**
 *
 * @author formiga
 */
public interface ContaService extends GenericService<Conta, ContaDTO> {
    
    List<SaqueResponse> sacar(SaqueRequest saqueRequest) throws SaqueException;
    DepositoDTO depositar(DepositoRequest depositoRequest) throws DepositoException;
    TransferenciaResponse transferir(TransferenciaRequest transferenciaRequest) throws TransferenciaException;
    
}