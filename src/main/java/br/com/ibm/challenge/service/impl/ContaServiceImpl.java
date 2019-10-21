
package br.com.ibm.challenge.service.impl;


import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.domain.enumeration.CedulasEnum;
import br.com.ibm.challenge.dto.ContaDTO;
import br.com.ibm.challenge.dto.DepositoDTO;
import br.com.ibm.challenge.exceptions.DepositoException;
import br.com.ibm.challenge.exceptions.DepositoExceptionMensagens;
import br.com.ibm.challenge.exceptions.SaqueException;
import br.com.ibm.challenge.exceptions.SaqueExceptionMensagens;
import br.com.ibm.challenge.exceptions.TransferenciaException;
import br.com.ibm.challenge.exceptions.TransferenciaExceptionMensagens;
import br.com.ibm.challenge.repository.ContaRepository;
import br.com.ibm.challenge.rest.request.DepositoRequest;
import br.com.ibm.challenge.rest.request.SaqueRequest;
import br.com.ibm.challenge.rest.request.TransferenciaRequest;
import br.com.ibm.challenge.rest.response.SaqueResponse;
import br.com.ibm.challenge.rest.response.TransferenciaResponse;
import br.com.ibm.challenge.service.ContaService;
import br.com.ibm.challenge.service.DepositoService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
    
    @Autowired
    private DepositoService depositoService;
    
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
    
    @Override
    public List<SaqueResponse> sacar(SaqueRequest saqueRequest) throws SaqueException {
        
        List<SaqueResponse> listaDeCedulas = new ArrayList<>();
        
        Long valorInteiro = saqueRequest.getValor().longValue();
        
        if (this.validarValorSolicitadoParaSaque(valorInteiro)) {
            
            Conta conta = this.contaRepository.findByAgenciaAndNumero(saqueRequest.getAgencia(), saqueRequest.getNumeroConta()).orElse(null);
        
            if (Objects.nonNull(conta)) {

                if (this.validarSaldoDisponivelParaSaque(valorInteiro, conta.getSaldo().longValue())) {

                    conta.setSaldo(conta.getSaldo().subtract(BigDecimal.valueOf(valorInteiro)));

                    ContaDTO contaDTO = this.converterEntidadeParaDTO(this.contaRepository.save(conta));
                    
                    listaDeCedulas = this.validarCedulas(valorInteiro, contaDTO.getId(), contaDTO.getSaldo());
                } else {
                    throw new SaqueException(SaqueExceptionMensagens.SALDO_INSUFICIENTE);
                }
            } else {
                throw new SaqueException(SaqueExceptionMensagens.CONTA_NAO_ENCONTRADA);
            }
        
        } else {
            throw new SaqueException(SaqueExceptionMensagens.VALOR_INVALIDO);
        }
        
        return listaDeCedulas;
    }
    
    @Override
    public DepositoDTO depositar(DepositoRequest depositoRequest) throws DepositoException {
        
        DepositoDTO depositoDTO = null;
        
        Conta conta = this.contaRepository.findByAgenciaAndNumero(depositoRequest.getAgencia(), depositoRequest.getNumeroConta()).orElse(null);
        
        if (Objects.nonNull(conta)) {
            
            conta.setSaldo(conta.getSaldo().add(depositoRequest.getValor()));

            ContaDTO contaDTO = this.converterEntidadeParaDTO(this.contaRepository.save(conta));
            
            depositoDTO = DepositoDTO.builder()
                    .contaDTO(contaDTO)
                    .valor(depositoRequest.getValor())
                    .dataHora(LocalDateTime.now())
                    .tipoDeDeposito(depositoRequest.getTipoDeposito())
                    .build();
        } else {
            throw new DepositoException(DepositoExceptionMensagens.CONTA_NAO_ENCONTRADA);
        }
        
        return this.depositoService.salvar(depositoDTO).orElse(null);
    }
    
    @Override
    public TransferenciaResponse transferir(TransferenciaRequest transferenciaRequest) throws TransferenciaException {
        
        TransferenciaResponse transferenciaResponse = null;
        
        Conta contaOrigem = this.contaRepository.findByAgenciaAndNumero(transferenciaRequest.getAgenciaOrigem(), transferenciaRequest.getNumeroContaOrigem()).orElse(null);
        
        if (Objects.nonNull(contaOrigem)) {
            
            Conta contaDestino = this.contaRepository.findByAgenciaAndNumero(transferenciaRequest.getAgenciaDestino(), transferenciaRequest.getNumeroContaDestino()).orElse(null);
            
            if (Objects.nonNull(contaDestino)) {
                
                if (this.validarSaldoDisponivelParaTransferencia(transferenciaRequest.getValor(), contaOrigem.getSaldo())) {
                    
                    contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(transferenciaRequest.getValor()));
                    contaDestino.setSaldo(contaDestino.getSaldo().add(transferenciaRequest.getValor()));
                    
                    List<Conta> contasParaSalvar = Arrays.asList(contaOrigem, contaDestino);
                    
                    List<ContaDTO> contasSalvas = this.converterListaEntidadeParaListaDTO(this.contaRepository.saveAll(contasParaSalvar));
                    
                    transferenciaResponse = TransferenciaResponse.builder()
                            .contaOrigem(contasSalvas.get(0))
                            .contaDestino(contasSalvas.get(1))
                            .valor(transferenciaRequest.getValor())
                            .dataHora(LocalDateTime.now())
                            .build();
                    
                }
                
            } else {
                throw new TransferenciaException(TransferenciaExceptionMensagens.CONTA_DESTINO_NAO_ENCONTRADA);
            }
            
        } else {
            throw new TransferenciaException(TransferenciaExceptionMensagens.CONTA_ORIGEM_NAO_ENCONTRADA);
        }
        
        return transferenciaResponse;
    }
    
    private Boolean validarValorSolicitadoParaSaque(Long valor) {
        
        return valor % 5 == 0;
    }
    
    private Boolean validarSaldoDisponivelParaSaque(Long valorDesejado, Long saldoDisponivel) {

        return valorDesejado.compareTo(saldoDisponivel) < 1;
    }
    
    private List<SaqueResponse> validarCedulas(long valor, Long contaId, BigDecimal saldoConta) {
        
        List<CedulasEnum> cedulas = Arrays.asList(CedulasEnum.values());
        cedulas.sort(Collections.reverseOrder());
        
        List<SaqueResponse> listaDeCedulas = new ArrayList<>();
        
        long valorCalculado = valor;
        
        for (CedulasEnum cedula : cedulas) {
            
            SaqueResponse saqueResponse = this.calcularQuantidadeDeCedulasPorValor(valorCalculado, cedula.getVALOR());
            saqueResponse.setCedula(cedula.name());
            saqueResponse.setContaId(contaId);
            saqueResponse.setSaldoDaConta(saldoConta);
            
            listaDeCedulas.add(saqueResponse);
            
            valorCalculado -= saqueResponse.getValorTotalPorCedula();
            
            if (valorCalculado <= 0) {
                break;
            }
        }
        
        return listaDeCedulas;
    }
    
    private SaqueResponse calcularQuantidadeDeCedulasPorValor(long valorSolicitado, int valorCedula) {
        
        int quantidadeCedula = 0;
        long valorTotalCedula = 0L;
        
        while ((int)valorSolicitado >= valorCedula) {
            
            quantidadeCedula ++;
            valorSolicitado -= valorCedula;
            valorTotalCedula += valorCedula;
            
        }
        
        SaqueResponse saqueResponse = SaqueResponse.builder()
                            .cedula(null)
                            .valor(valorCedula)
                            .quantidade(quantidadeCedula)
                            .valorTotalPorCedula(valorTotalCedula)
                            .contaId(null)
                            .build();
        
        return saqueResponse;
    }
    
    private Boolean validarSaldoDisponivelParaTransferencia(BigDecimal valorDesejado, BigDecimal saldoDisponivel) {

        return valorDesejado.compareTo(saldoDisponivel) < 1;
    }

}