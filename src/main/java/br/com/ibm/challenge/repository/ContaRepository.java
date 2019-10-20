
package br.com.ibm.challenge.repository;


import br.com.ibm.challenge.domain.Conta;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author formiga
 */
@Repository(value = "contaRepository")
public interface ContaRepository extends JpaRepository<Conta, Long> {
    
    Optional<Conta> findByAgenciaAndNumero(Integer agencia, Integer numero);
}