
package br.com.ibm.challenge.domain;


import br.com.ibm.challenge.domain.Cliente;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;


/**
 *
 * @author formiga
 */
@Entity
@Table(name = "conta", schema = "ibm-atm-challenge")
@Data
public class Conta implements Serializable {

    private static final long serialVersionUID = 5111864611664997517L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agencia", length = 4, nullable = false)
    private Integer agencia;

    @Column(name = "numero", length = 6, nullable = false)
    private Integer numero;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    @Basic(optional = false)
    private Cliente cliente;

    
    public Conta() {
        super();
    }

    @Builder
    public Conta(Long id, Integer agencia, Integer numero, BigDecimal saldo, Cliente cliente) {
        this.id = id;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.cliente = cliente;
    }
    
}