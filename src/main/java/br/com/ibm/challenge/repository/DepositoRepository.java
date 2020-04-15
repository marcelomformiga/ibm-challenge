
package br.com.ibm.challenge.repository;


import br.com.ibm.challenge.domain.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author formiga
 */
@Repository(value = "depositoRepository")
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    
}