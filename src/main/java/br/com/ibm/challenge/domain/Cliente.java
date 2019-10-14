
package br.com.ibm.challenge.domain;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;


/**
 *
 * @author formiga
 */
@Entity
@Table(name = "cliente", schema = "ibm-atm-challenge")
@Data
public class Cliente implements Serializable {

    private static final long serialVersionUID = -2785537935992000555L;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "email", length = 60)
    private String email;

    
    public Cliente() {
        super();
    }
    
    @Builder
    public Cliente(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
    
}