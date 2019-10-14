
package br.com.ibm.challenge.service.impl;


import br.com.ibm.challenge.domain.Cliente;
import br.com.ibm.challenge.dto.ClienteDTO;
import br.com.ibm.challenge.repository.ClienteRepository;
import br.com.ibm.challenge.service.ClienteService;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author formiga
 */
@Service(value = "clienteService")
public class ClienteServiceImpl implements ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Getter
    private ModelMapper modelMapper;
    

    @Override
    public ClienteDTO converterEntidadeParaDTO(Cliente entidade) {
        this.modelMapper = new ModelMapper();
        
        return this.modelMapper.map(entidade, ClienteDTO.class);
    }

    @Override
    public Cliente converterDTOParaEntidade(ClienteDTO dto) {
        this.modelMapper = new ModelMapper();
        
        return this.modelMapper.map(dto, Cliente.class);
    }

    @Override
    public List<ClienteDTO> converterListaEntidadeParaListaDTO(List<Cliente> lista) {
        
        List<ClienteDTO> clientes = new ArrayList<>();
        
        lista.stream().map((entidade) -> this.converterEntidadeParaDTO(entidade)).forEachOrdered((cliente) -> {
            clientes.add(cliente);
        });
        
        return clientes;
    }

    @Override
    public List<Cliente> converterListaDTOParaListaEntidade(List<ClienteDTO> lista) {
        
        List<Cliente> clientes = new ArrayList<>();
        
        lista.stream().map((dto) -> this.converterDTOParaEntidade(dto)).forEachOrdered((cliente) -> {
            clientes.add(cliente);
        });
        
        return clientes;
    }
    
}
