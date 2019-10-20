
import br.com.ibm.challenge.domain.Cliente;
import br.com.ibm.challenge.domain.Conta;
import br.com.ibm.challenge.dto.ContaDTO;
import br.com.ibm.challenge.repository.ContaRepository;
import br.com.ibm.challenge.rest.request.SaqueRequest;
import br.com.ibm.challenge.service.impl.ContaServiceImpl;
import java.math.BigDecimal;
import java.util.Optional;
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
    public void testSacar() {
        
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
        
//        ClienteDTO clienteDTO = ClienteDTO.builder()
//                .id(cliente.getId())
//                .nome(cliente.getNome())
//                .email(cliente.getEmail())
//                .build();
//        
//        ContaDTO contaDTO = ContaDTO.builder()
//                .id(conta.getId())
//                .agencia(conta.getAgencia())
//                .numero(conta.getNumero())
//                .saldo(conta.getSaldo())
//                .clienteDTO(clienteDTO)
//                .build();
        
        SaqueRequest saqueRequest = new SaqueRequest();
        saqueRequest.setAgencia(this.numeroAgencia);
        saqueRequest.setNumeroConta(this.numeroConta);
        saqueRequest.setValor(BigDecimal.valueOf(100.00));
        
        Mockito.when(this.contaRepository.findByAgenciaAndNumero(this.numeroAgencia, this.numeroConta)).thenReturn(Optional.of(conta));
        Mockito.when(this.contaRepository.save(conta)).thenReturn(contaSalva);
        
        ContaDTO resposta = this.contaServiceImpl.sacar(saqueRequest);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
    }
    
}