
package br.com.ibm.challenge.rest.controller;


import br.com.ibm.challenge.dto.ClienteDTO;
import br.com.ibm.challenge.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author formiga
 */
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    
    @GetMapping()
    public List<ClienteDTO> listar() {
        return null;
    }
    
    @GetMapping("/{id}")
    public ClienteDTO recuperarPorId(@PathVariable String id) {
        return null;
    }
    
    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody ClienteDTO cliente) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ClienteDTO cliente) {
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable String id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
}