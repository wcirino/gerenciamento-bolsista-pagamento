package com.gerenciamentoBolsista;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gerenciamentoBolsista.dto.BolsistaDTO;
import com.gerenciamentoBolsista.entity.Bolsista;
import com.gerenciamentoBolsista.repository.BolsistaRepository;
import com.gerenciamentoBolsista.service.BolsistaService;

@SpringBootApplication
public class GerenciamentoBolsistaApplication implements CommandLineRunner {
	
	@Autowired
	private BolsistaService bolsistaService;

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoBolsistaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BolsistaDTO bolsista1 = new BolsistaDTO();
		bolsista1.setNomeCompleto("João da Silva");
		bolsista1.setTipoIdentificador(1);
		bolsista1.setNumeroIdentificador("123.456.789-00");
		//bolsista1.setDataCadastro(LocalDate.parse("2024-04-18"));
		bolsista1.setCodigoBanco("001");
		bolsista1.setNumeroAgencia("1234");
		bolsista1.setNumeroConta("12345-6");
		bolsista1.setStatus(true);
		bolsistaService.salvar(bolsista1);

		BolsistaDTO bolsista2 = new BolsistaDTO();
		bolsista2.setNomeCompleto("Maria Souza");
		bolsista2.setTipoIdentificador(2);
		bolsista2.setNumeroIdentificador("98765432");
		//bolsista2.setDataCadastro(LocalDate.parse("2024-04-19"));
		bolsista2.setCodigoBanco("002");
		bolsista2.setNumeroAgencia("5678");
		bolsista2.setNumeroConta("98765-4");
		bolsista2.setStatus(true);
		bolsistaService.salvar(bolsista2);

		BolsistaDTO bolsista3 = new BolsistaDTO();
		bolsista3.setNomeCompleto("José Pereira");
		bolsista3.setTipoIdentificador(3);
		bolsista3.setNumeroIdentificador("123456789");
		//bolsista3.setDataCadastro(LocalDate.parse("2024-04-20"));
		bolsista3.setCodigoBanco("003");
		bolsista3.setNumeroAgencia("91011");
		bolsista3.setNumeroConta("65432-1");
		bolsista3.setStatus(true);
		bolsistaService.salvar(bolsista3);
	}

}
