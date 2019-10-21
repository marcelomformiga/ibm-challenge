
import br.com.ibm.challenge.domain.Cliente;
import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.domain.enumeration.TipoDepositoEnum;
import br.com.ibm.challenge.dto.ClienteDTO;
import br.com.ibm.challenge.dto.ContaDTO;
import br.com.ibm.challenge.dto.DepositoDTO;
import br.com.ibm.challenge.exceptions.DepositoException;
import br.com.ibm.challenge.exceptions.SaqueException;
import br.com.ibm.challenge.exceptions.TransferenciaException;
import br.com.ibm.challenge.repository.ContaRepository;
import br.com.ibm.challenge.rest.request.DepositoRequest;
import br.com.ibm.challenge.rest.request.SaqueRequest;
import br.com.ibm.challenge.rest.request.TransferenciaRequest;
import br.com.ibm.challenge.rest.response.SaqueResponse;
import br.com.ibm.challenge.rest.response.TransferenciaResponse;
import br.com.ibm.challenge.service.DepositoService;
import br.com.ibm.challenge.service.impl.ContaServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


/**
 *
 * @author formiga
 */
@RunWith(MockitoJUnitRunner.class)
public class ContaServiceImplTest {
    
    @InjectMocks
    private ContaServiceImpl contaServiceImpl;
    
    @Mock
    private ContaRepository contaRepository;
    
    @Mock
    private DepositoService depositoService;
    
    private Integer numeroAgencia;
    private Integer numeroConta;
    
    
    @Before
    public void setUp() {
        this.numeroAgencia = 1111;
        this.numeroConta = 1234;
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testSacar_Sucesso() {
        
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Marcelo Formiga")
                .email("marcelo.formiga@email.com")
                .build();
        
        Conta conta = Conta.builder()
                .id(2L)
                .agencia(1111)
                .numero(1234)
                .saldo(BigDecimal.valueOf(1000.00))
                .cliente(cliente)
                .build();
        
        Conta contaSalva = Conta.builder()
                .id(2L)
                .agencia(1111)
                .numero(1234)
                .saldo(BigDecimal.valueOf(900.00))
                .cliente(cliente)
                .build();
        
        SaqueRequest saqueRequest = new SaqueRequest();
        saqueRequest.setAgencia(this.numeroAgencia);
        saqueRequest.setNumeroConta(this.numeroConta);
        saqueRequest.setValor(190.70);
        
        Mockito.when(this.contaRepository.findByAgenciaAndNumero(this.numeroAgencia, this.numeroConta)).thenReturn(Optional.of(conta));
        Mockito.when(this.contaRepository.save(conta)).thenReturn(contaSalva);
        
        List<SaqueResponse> resposta = null;
        
        try {
            resposta = this.contaServiceImpl.sacar(saqueRequest);
        } catch (SaqueException ex) {
            Logger.getLogger(ContaServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertEquals("[2]Tamanho da lista deve ser 3!", 3, resposta.size());
        Assert.assertEquals("[3]Objetos comparados devem ser iguais!", contaSalva.getId(), resposta.get(0).getContaId());
        Assert.assertEquals("[4]Objetos comparados devem ser iguais!", contaSalva.getId(), resposta.get(1).getContaId());
        Assert.assertEquals("[5]Objetos comparados devem ser iguais!", contaSalva.getId(), resposta.get(2).getContaId());
        Assert.assertEquals("[6]Objetos comparados devem ser iguais!", contaSalva.getSaldo(), resposta.get(0).getSaldoDaConta());
        Assert.assertEquals("[7]Objetos comparados devem ser iguais!", contaSalva.getSaldo(), resposta.get(1).getSaldoDaConta());
        Assert.assertEquals("[8]Objetos comparados devem ser iguais!", contaSalva.getSaldo(), resposta.get(2).getSaldoDaConta());
        
        Mockito.verify(this.contaRepository, Mockito.times(1)).findByAgenciaAndNumero(this.numeroAgencia, this.numeroConta);
        Mockito.verify(this.contaRepository, Mockito.times(1)).save(conta);
    }
    
    @Test
    public void testDepositar_Sucesso() {
        
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Marcelo Formiga")
                .email("marcelo.formiga@email.com")
                .build();
        
        Conta conta = Conta.builder()
                .id(2L)
                .agencia(1111)
                .numero(1234)
                .saldo(BigDecimal.valueOf(1000.00))
                .cliente(cliente)
                .build();
        
        Conta contaSalva = Conta.builder()
                .id(2L)
                .agencia(1111)
                .numero(1234)
                .saldo(BigDecimal.valueOf(900.00))
                .cliente(cliente)
                .build();
        
        ClienteDTO clienteDTO = ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .build();
        
        ContaDTO contaDTO = ContaDTO.builder()
                .id(contaSalva.getId())
                .agencia(contaSalva.getAgencia())
                .numero(contaSalva.getNumero())
                .saldo(contaSalva.getSaldo())
                .clienteDTO(clienteDTO)
                .build();
        
        DepositoRequest depositoRequest = new DepositoRequest();
        depositoRequest.setAgencia(this.numeroAgencia);
        depositoRequest.setNumeroConta(this.numeroConta);
        depositoRequest.setValor(BigDecimal.valueOf(250.00));
        depositoRequest.setTipoDeposito(TipoDepositoEnum.CHEQUE.name());
        
        DepositoDTO depositoSalvo = DepositoDTO.builder()
                .id(4L)
                .contaDTO(contaDTO)
                .dataHora(LocalDateTime.of(2019, Month.OCTOBER, 10, 11, 12, 0))
                .valor(depositoRequest.getValor())
                .tipoDeDeposito(depositoRequest.getTipoDeposito())
                .build();
        
        Mockito.when(this.contaRepository.findByAgenciaAndNumero(this.numeroAgencia, this.numeroConta)).thenReturn(Optional.of(conta));
        Mockito.when(this.contaRepository.save(conta)).thenReturn(contaSalva);
        Mockito.when(this.depositoService.salvar(Mockito.any(DepositoDTO.class))).thenReturn(Optional.of(depositoSalvo));
        
        DepositoDTO resposta = null;
        
        try {
            resposta = this.contaServiceImpl.depositar(depositoRequest);
        } catch (DepositoException ex) {
            Logger.getLogger(ContaServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertEquals("[2]Objetos comparados devem ser iguais!", depositoSalvo, resposta);
        
        Mockito.verify(this.contaRepository, Mockito.times(1)).findByAgenciaAndNumero(this.numeroAgencia, this.numeroConta);
        Mockito.verify(this.contaRepository, Mockito.times(1)).save(conta);
        Mockito.verify(this.depositoService, Mockito.times(1)).salvar(Mockito.any(DepositoDTO.class));
    }
    
    @Test
    public void testTransferencia_Sucesso() {
        
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setAgenciaOrigem(1111);
        transferenciaRequest.setNumeroContaOrigem(1234);
        transferenciaRequest.setAgenciaDestino(2222);
        transferenciaRequest.setNumeroContaDestino(4321);
        transferenciaRequest.setValor(BigDecimal.valueOf(250.00));
        
        LocalDateTime dataHoraTransferencia = LocalDateTime.of(2019, Month.OCTOBER, 10, 11, 12, 0);
        
        Cliente clienteOrigem = Cliente.builder()
                .id(1L)
                .nome("Marcelo Formiga")
                .email("marcelo.formiga@email.com")
                .build();
        
        Cliente clienteDestino = Cliente.builder()
                .id(2L)
                .nome("Renato Portaluppi")
                .email("renato.portaluppi@email.com")
                .build();
        
        Conta contaOrigem = Conta.builder()
                .id(1L)
                .agencia(1111)
                .numero(1234)
                .saldo(BigDecimal.valueOf(1000.00))
                .cliente(clienteOrigem)
                .build();
        
        Conta contaDestino = Conta.builder()
                .id(2L)
                .agencia(2222)
                .numero(4321)
                .saldo(BigDecimal.valueOf(500.00))
                .cliente(clienteDestino)
                .build();
        
        ClienteDTO clienteDTOOrigem = ClienteDTO.builder()
                .id(clienteOrigem.getId())
                .nome(clienteOrigem.getNome())
                .email(clienteOrigem.getEmail())
                .build();
        
        ClienteDTO clienteDTODestino = ClienteDTO.builder()
                .id(clienteDestino.getId())
                .nome(clienteDestino.getNome())
                .email(clienteDestino.getEmail())
                .build();
        
        Conta contaOrigemSalva = Conta.builder()
                .id(contaOrigem.getId())
                .agencia(contaOrigem.getAgencia())
                .numero(contaOrigem.getNumero())
                .saldo(contaOrigem.getSaldo().subtract(transferenciaRequest.getValor()))
                .cliente(clienteOrigem)
                .build();
        
        Conta contaDestinoSalva = Conta.builder()
                .id(contaDestino.getId())
                .agencia(contaDestino.getAgencia())
                .numero(contaDestino.getNumero())
                .saldo(contaDestino.getSaldo().add(transferenciaRequest.getValor()))
                .cliente(clienteDestino)
                .build();
        
        ContaDTO contaDTOOrigem = ContaDTO.builder()
                .id(contaOrigemSalva.getId())
                .agencia(contaOrigemSalva.getAgencia())
                .numero(contaOrigemSalva.getNumero())
                .saldo(contaOrigemSalva.getSaldo())
                .clienteDTO(clienteDTOOrigem)
                .build();
        
        ContaDTO contaDTODestino = ContaDTO.builder()
                .id(contaDestinoSalva.getId())
                .agencia(contaDestinoSalva.getAgencia())
                .numero(contaDestinoSalva.getNumero())
                .saldo(contaDestinoSalva.getSaldo())
                .clienteDTO(clienteDTODestino)
                .build();
        
        List<Conta> contasParaSalvar = new ArrayList<>();
        contasParaSalvar.add(contaOrigem);
        contasParaSalvar.add(contaDestino);
        
        List<Conta> contasSalvas = new ArrayList<>();
        contasSalvas.add(contaOrigemSalva);
        contasSalvas.add(contaDestinoSalva);
        
        TransferenciaResponse transferenciaResponse = TransferenciaResponse.builder()
                .contaOrigem(contaDTOOrigem)
                .contaDestino(contaDTODestino)
                .valor(transferenciaRequest.getValor())
                .dataHora(dataHoraTransferencia)
                .build();
        
        Mockito.when(this.contaRepository.findByAgenciaAndNumero(contaOrigem.getAgencia(), contaOrigem.getNumero())).thenReturn(Optional.of(contaOrigem));
        Mockito.when(this.contaRepository.findByAgenciaAndNumero(contaDestino.getAgencia(), contaDestino.getNumero())).thenReturn(Optional.of(contaDestino));
        Mockito.when(this.contaRepository.saveAll(contasParaSalvar)).thenReturn(contasSalvas);

        TransferenciaResponse resposta = null;
        
        try {
            resposta = this.contaServiceImpl.transferir(transferenciaRequest);
        } catch (TransferenciaException ex) {
            Logger.getLogger(ContaServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertEquals("[2]Objetos comparados devem ser iguais!", transferenciaResponse.getContaOrigem().getId(), resposta.getContaOrigem().getId());
        Assert.assertEquals("[3]Objetos comparados devem ser iguais!", transferenciaResponse.getContaDestino().getId(), resposta.getContaDestino().getId());
        Assert.assertEquals("[4]Objetos comparados devem ser iguais!", transferenciaResponse.getValor(), resposta.getValor());
        
        Mockito.verify(this.contaRepository, Mockito.times(2)).findByAgenciaAndNumero(Mockito.anyInt(), Mockito.anyInt());
        Mockito.verify(this.contaRepository, Mockito.times(1)).saveAll(Mockito.anyIterable());
    }
    
}