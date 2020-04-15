
package br.com.ibm.challenge.service;


import br.com.ibm.challenge.domain.Deposito;
import br.com.ibm.challenge.dto.DepositoDTO;
import java.util.Optional;


/**
 *
 * @author formiga
 */
public interface DepositoService extends GenericService<Deposito, DepositoDTO> {
    
    Optional<DepositoDTO> salvar(DepositoDTO depositoDTO);
}