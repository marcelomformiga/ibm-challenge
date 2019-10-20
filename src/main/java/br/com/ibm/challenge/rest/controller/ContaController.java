
package br.com.ibm.challenge.rest.controller;


import br.com.ibm.challenge.dto.ContaDTO;
import br.com.ibm.challenge.service.ContaService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;


/**
 *
 * @author formiga
 */
@RestController
@RequestMapping("/conta")
public class ContaController {
    
    @Autowired
    private ContaService contaService;
    
    
    @GetMapping()
    public List<ContaDTO> listar() {
        return null;
    }
    
    @GetMapping("/{id}")
    public ContaDTO recuperarPorId(@PathVariable String id) {
        return null;
    }
    
    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody ContaDTO conta) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ContaDTO conta) {
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable String id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
}