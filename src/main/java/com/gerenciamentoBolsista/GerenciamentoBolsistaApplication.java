package com.gerenciamentoBolsista;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gerenciamentoBolsista.dto.BolsistaDTO;
import com.gerenciamentoBolsista.entity.Pagamento;
import com.gerenciamentoBolsista.entity.enums.StatusPagamento;
import com.gerenciamentoBolsista.repository.PagamentoRepository;
import com.gerenciamentoBolsista.service.BolsistaService;

@SpringBootApplication
public class GerenciamentoBolsistaApplication implements CommandLineRunner {
	
	@Autowired
	private BolsistaService bolsistaService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoBolsistaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BolsistaDTO bolsista1 = new BolsistaDTO();
		bolsista1.setNomeCompleto("João da Silva");
		bolsista1.setTipoIdentificador(1);
		bolsista1.setNumeroIdentificador("123.456.789-00");
		bolsista1.setCodigoBanco("001");
		bolsista1.setNumeroAgencia("1234");
		bolsista1.setNumeroConta("12345-6");
		bolsista1.setStatus(true);
		bolsistaService.salvar(bolsista1);

		BolsistaDTO bolsista2 = new BolsistaDTO();
		bolsista2.setNomeCompleto("Maria Souza");
		bolsista2.setTipoIdentificador(2);
		bolsista2.setNumeroIdentificador("98765432");
		bolsista2.setCodigoBanco("002");
		bolsista2.setNumeroAgencia("5678");
		bolsista2.setNumeroConta("98765-4");
		bolsista2.setStatus(true);
		bolsistaService.salvar(bolsista2);

		BolsistaDTO bolsista3 = new BolsistaDTO();
		bolsista3.setNomeCompleto("José Pereira");
		bolsista3.setTipoIdentificador(3);
		bolsista3.setNumeroIdentificador("123456789");
		bolsista3.setCodigoBanco("003");
		bolsista3.setNumeroAgencia("91011");
		bolsista3.setNumeroConta("65432-1");
		bolsista3.setStatus(true);
		bolsistaService.salvar(bolsista3);
		
		 Pagamento pagamento1 = new Pagamento();
		    pagamento1.setBolsistaId(1L);
		    pagamento1.setDataPagamento(LocalDate.now());
		    pagamento1.setBanco("Banco A");
		    pagamento1.setAgencia("1234");
		    pagamento1.setConta("5678");
		    pagamento1.setValor(BigDecimal.valueOf(1000.00));
		    pagamento1.setStatus(StatusPagamento.SOLICITADO);

		    Pagamento pagamento2 = new Pagamento();
		    pagamento2.setBolsistaId(1L);
		    pagamento2.setDataPagamento(LocalDate.now().minusDays(1));
		    pagamento2.setBanco("Banco B");
		    pagamento2.setAgencia("4321");
		    pagamento2.setConta("8765");
		    pagamento2.setValor(BigDecimal.valueOf(1500.00));
		    pagamento2.setStatus(StatusPagamento.SOLICITADO);

		    Pagamento pagamento3 = new Pagamento();
		    pagamento3.setBolsistaId(2L);
		    pagamento3.setDataPagamento(LocalDate.now());
		    pagamento3.setBanco("Banco C");
		    pagamento3.setAgencia("1111");
		    pagamento3.setConta("2222");
		    pagamento3.setValor(BigDecimal.valueOf(800.00));
		    pagamento3.setStatus(StatusPagamento.CANCELADO);

		    Pagamento pagamento4 = new Pagamento();
		    pagamento4.setBolsistaId(2L);
		    pagamento4.setDataPagamento(LocalDate.now().minusDays(2));
		    pagamento4.setBanco("Banco D");
		    pagamento4.setAgencia("3333");
		    pagamento4.setConta("4444");
		    pagamento4.setValor(BigDecimal.valueOf(1200.00));
		    pagamento4.setStatus(StatusPagamento.PAGO);

		    Pagamento pagamento5 = new Pagamento();
		    pagamento5.setBolsistaId(3L);
		    pagamento5.setDataPagamento(LocalDate.now());
		    pagamento5.setBanco("Banco E");
		    pagamento5.setAgencia("5555");
		    pagamento5.setConta("6666");
		    pagamento5.setValor(BigDecimal.valueOf(2000.00));
		    pagamento5.setStatus(StatusPagamento.NAO_REALIZADO);

		    pagamentoRepository.saveAll(List.of(pagamento1, pagamento2, pagamento3, pagamento4, pagamento5));
	}

}
