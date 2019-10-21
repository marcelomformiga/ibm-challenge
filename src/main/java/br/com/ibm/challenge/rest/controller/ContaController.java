
package br.com.ibm.challenge.rest.controller;


import br.com.ibm.challenge.dto.DepositoDTO;
import br.com.ibm.challenge.exceptions.DepositoException;
import br.com.ibm.challenge.exceptions.SaqueException;
import br.com.ibm.challenge.exceptions.TransferenciaException;
import br.com.ibm.challenge.rest.request.DepositoRequest;
import br.com.ibm.challenge.rest.request.SaqueRequest;
import br.com.ibm.challenge.rest.request.TransferenciaRequest;
import br.com.ibm.challenge.rest.response.SaqueResponse;
import br.com.ibm.challenge.rest.response.TransferenciaResponse;
import br.com.ibm.challenge.service.ContaService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


/**
 *
 * @author formiga
 */
@RestController
@RequestMapping(value = "conta")
public class ContaController {
    
    @Autowired
    private ContaService contaService;
    
    
    @PutMapping(value = "saque", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes= {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<SaqueResponse>> sacar(@RequestBody @Valid SaqueRequest saqueRequest) throws SaqueException {
        
        return new ResponseEntity<>(this.contaService.sacar(saqueRequest), HttpStatus.OK);
    }
    
    @PutMapping(value = "deposito", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes= {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<DepositoDTO> depositar(@RequestBody @Valid DepositoRequest depositoRequest) throws DepositoException {
        
        return new ResponseEntity<>(this.contaService.depositar(depositoRequest), HttpStatus.OK);
    }
    
    @PutMapping(value = "transferencia", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes= {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TransferenciaResponse> transferir(@RequestBody @Valid TransferenciaRequest transferenciaRequest) throws TransferenciaException {
        
        return new ResponseEntity<>(this.contaService.transferir(transferenciaRequest), HttpStatus.OK);
    }
    
}