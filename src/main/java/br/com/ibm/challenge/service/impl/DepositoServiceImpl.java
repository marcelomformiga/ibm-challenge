
package br.com.ibm.challenge.service.impl;


import br.com.ibm.challenge.domain.Deposito;
import br.com.ibm.challenge.dto.DepositoDTO;
import br.com.ibm.challenge.repository.DepositoRepository;
import br.com.ibm.challenge.service.DepositoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author formiga
 */
@Service(value = "depositoService")
public class DepositoServiceImpl implements DepositoService {
    
    @Autowired
    private DepositoRepository depositoRepository;
    
    @Getter
    private ModelMapper modelMapper;
    
    
    @Override
    public DepositoDTO converterEntidadeParaDTO(Deposito entidade) {
        this.modelMapper = new ModelMapper();
        
        return this.modelMapper.map(entidade, DepositoDTO.class);
    }

    @Override
    public Deposito converterDTOParaEntidade(DepositoDTO dto) {
        this.modelMapper = new ModelMapper();
        
        return this.modelMapper.map(dto, Deposito.class);
    }

    @Override
    public List<DepositoDTO> converterListaEntidadeParaListaDTO(List<Deposito> lista) {
        
        List<DepositoDTO> depositos = new ArrayList<>();
        
        lista.stream().map((entidade) -> this.converterEntidadeParaDTO(entidade)).forEachOrdered((deposito) -> {
            depositos.add(deposito);
        });
        
        return depositos;
    }

    @Override
    public List<Deposito> converterListaDTOParaListaEntidade(List<DepositoDTO> lista) {
        
        List<Deposito> depositos = new ArrayList<>();
        
        lista.stream().map((dto) -> this.converterDTOParaEntidade(dto)).forEachOrdered((deposito) -> {
            depositos.add(deposito);
        });
        
        return depositos;
    }
    
    @Override
    public Optional<DepositoDTO> salvar(DepositoDTO depositoDTO) {
        
        Deposito depositoSalvo = this.depositoRepository.save(this.converterDTOParaEntidade(depositoDTO));
        
        return Optional.of(this.converterEntidadeParaDTO(depositoSalvo));
    }

}