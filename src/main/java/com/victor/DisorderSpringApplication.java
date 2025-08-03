package com.victor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.victor.model.Carteira;
import com.victor.model.Transacao;
import com.victor.repository.CarteiraRepository;
import com.victor.repository.TransacaoRepository;

@SpringBootApplication
public class DisorderSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisorderSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(TransacaoRepository transacaoRepository, CarteiraRepository carteiraRepository) {
		return args -> {
			transacaoRepository.deleteAll();

			Transacao t = new Transacao();
			t.setDescricao("Compra de café");
			t.setValor(5.50);
			t.setDataTransacao(new java.util.Date());
			t.setTipo('D'); // 'D' para débito
			t.setIdCategoria(1);
			t.setIdCarteira(1);
			t.setIdUsuario(1);
			t.setAtivo(true);

			Carteira c = new Carteira();
			c.setTitulo("Carteira Pessoal");
			c.setContaCorrente(100.00);
			c.setContaPoupanca(200.00);
			c.setContaInvestimento(300.00);
			c.setLimiteCreditoTotal(500.00);
			c.setIdUsuario(1);
			c.setAtivo(true);

			transacaoRepository.save(t);
			carteiraRepository.save(c);
		};
	}
}
