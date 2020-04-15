
package br.com.ibm.challenge.rest.response;


import br.com.ibm.challenge.dto.ContaDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;


/**
 *
 * @author formiga
 */
@Data
public class DepositoResponse {
    
    private ContaDTO contaDTO;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    
    
    public DepositoResponse() {
        super();
    }
    
    @Builder
    public DepositoResponse(ContaDTO contaDTO, BigDecimal valor, LocalDateTime dataHora) {
        this.contaDTO = contaDTO;
        this.valor = valor;
        this.dataHora = dataHora;
    }
    
    
    @Override
    public String toString() {
        
        StringBuilder builder = new StringBuilder(100);
        builder.append("Agência: ");
        builder.append(contaDTO.getAgencia().toString());
        builder.append("\n");
        builder.append("Número: ");
        builder.append(contaDTO.getNumero().toString());
        builder.append("\n");
        builder.append("Saldo: ");
        builder.append(contaDTO.getSaldo().toString());
        builder.append("\n");
        builder.append("Valor: ");
        builder.append(valor.toString());
        builder.append("\n");
        builder.append("Data/Hora: ");
        builder.append(dataHora.toString());
        
        return builder.toString();
    }
    
}