package com.victor;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.victor.enums.TipoTransacao;
import com.victor.model.Carteira;
import com.victor.model.Categoria;
import com.victor.model.Transacao;
import com.victor.model.Usuario;
import com.victor.repository.CarteiraRepository;
import com.victor.repository.CategoriaRepository;
import com.victor.repository.TransacaoRepository;
import com.victor.repository.UsuarioRepository;

@SpringBootApplication
public class DisorderSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisorderSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(UsuarioRepository usuarioRepository, TransacaoRepository transacaoRepository,
			CarteiraRepository carteiraRepository, CategoriaRepository categoriaRepository) {
		return args -> {
			usuarioRepository.deleteAll();
			transacaoRepository.deleteAll();
			carteiraRepository.deleteAll();
			categoriaRepository.deleteAll();

			Usuario u = new Usuario();
			u.setUsername("victor");
			u.setPassword("victor");
			u.setNomeCompleto("victor");
			u.setEmail("victor@gmail.com");
			u.setTelefone("4002-8922");
			u.setChavePix("4002-8922");

			Usuario acessor = new Usuario();
			acessor.setUsername("gustavo");
			acessor.setPassword("gustavo");
			acessor.setNomeCompleto("gustavo");
			acessor.setEmail("gustavo@gmail.com");
			acessor.setTelefone("4002-8922");
			acessor.setChavePix("4002-8922");

			Carteira ct = new Carteira();
			ct.setTitulo("Caixa");
			ct.setContaCorrente(100.00);
			ct.setContaPoupanca(200.00);
			ct.setContaInvestimento(300.00);
			ct.setLimiteCreditoTotal(500.00);

			Categoria cg = new Categoria();
			cg.setTitulo("Transporte");
			cg.setValorPlanejado(100.00);

			Transacao t = new Transacao();
			t.setDescricao("Compra de café");
			t.setValor(5.50);
			t.setDataTransacao(new Date());
			t.setTipo(TipoTransacao.DEBITO);

			u.addCarteira(ct);
			ct.setUsuario(u);

			u.addCategoria(cg);
			cg.setUsuario(u);

			u.addTransacao(t);
			t.setUsuario(u);

			ct.getTransacoes().add(t);
			t.setCarteira(ct);

			cg.getTransacoes().add(t);
			t.setCategoria(cg);

			u.setAcessor(acessor);
			acessor.addCliente(u);

			usuarioRepository.save(acessor);
			usuarioRepository.save(u);
			carteiraRepository.save(ct);
			categoriaRepository.save(cg);
			transacaoRepository.save(t);
		};
	}
}
