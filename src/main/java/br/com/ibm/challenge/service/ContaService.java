
package br.com.ibm.challenge.service;


import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.dto.ContaDTO;
import br.com.ibm.challenge.rest.request.SaqueRequest;


/**
 *
 * @author formiga
 */
public interface ContaService extends GenericService<Conta, ContaDTO> {
    
    ContaDTO sacar(SaqueRequest saqueRequest);
}