
package br.com.ibm.challenge.repository;


import br.com.ibm.challenge.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author formiga
 */
@Repository(value = "clienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}