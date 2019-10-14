
package br.com.ibm.challenge.service.impl;


import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.dto.ContaDTO;
import br.com.ibm.challenge.repository.ContaRepository;
import br.com.ibm.challenge.service.ContaService;
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
@Service(value = "contaService")
public class ContaServiceImpl implements ContaService {
    
    @Autowired
    private ContaRepository contaRepository;
    
    @Getter
    private ModelMapper modelMapper;
    
    
    @Override
    public ContaDTO converterEntidadeParaDTO(Conta entidade) {
        this.modelMapper = new ModelMapper();
        
        return this.modelMapper.map(entidade, ContaDTO.class);
    }

    @Override
    public Conta converterDTOParaEntidade(ContaDTO dto) {
        this.modelMapper = new ModelMapper();
        
        return this.modelMapper.map(dto, Conta.class);
    }

    @Override
    public List<ContaDTO> converterListaEntidadeParaListaDTO(List<Conta> lista) {
        
        List<ContaDTO> contas = new ArrayList<>();
        
        lista.stream().map((entidade) -> this.converterEntidadeParaDTO(entidade)).forEachOrdered((conta) -> {
            contas.add(conta);
        });
        
        return contas;
    }

    @Override
    public List<Conta> converterListaDTOParaListaEntidade(List<ContaDTO> lista) {
        
        List<Conta> contas = new ArrayList<>();
        
        lista.stream().map((dto) -> this.converterDTOParaEntidade(dto)).forEachOrdered((conta) -> {
            contas.add(conta);
        });
        
        return contas;
    }
    
}