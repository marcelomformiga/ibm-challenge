
package br.com.ibm.challenge.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;


/**
 *
 * @author formiga
 */
@Entity
@Table(name = "deposito", schema = "ibm-atm-challenge")
@Data
public class Deposito implements Serializable {

    private static final long serialVersionUID = 3219639814041369677L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_id", referencedColumnName = "id", nullable = false)
    @Basic(optional = false)
    private Conta conta;
    
    @Column(name = "tipo_deposito", length = 8, nullable = false)
    private String tipoDeposito;
    
    @Column(name = "data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataHora;
    
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    
    public Deposito() {
        super();
    }

    @Builder
    public Deposito(Long id, Conta conta, String tipoDeposito, LocalDateTime dataHora) {
        this.id = id;
        this.conta = conta;
        this.tipoDeposito = tipoDeposito;
        this.dataHora = dataHora;
    }
    
}