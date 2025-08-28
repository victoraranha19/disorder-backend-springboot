package com.victor;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.victor.enums.TipoTransacao;
import com.victor.model.Instituicao;
import com.victor.model.Categoria;
import com.victor.model.Conta;
import com.victor.model.Transacao;
import com.victor.model.Usuario;
import com.victor.repository.InstituicaoRepository;
import com.victor.repository.CategoriaRepository;
import com.victor.repository.ContaRepository;
import com.victor.repository.TransacaoRepository;
import com.victor.repository.UsuarioRepository;

@SpringBootApplication
public class DisorderSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisorderSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(UsuarioRepository usuarioRepository, TransacaoRepository transacaoRepository,
			ContaRepository contaRepository, CategoriaRepository categoriaRepository,
			InstituicaoRepository instituicaoRepository) {
		return args -> {
			usuarioRepository.deleteAll();
			transacaoRepository.deleteAll();
			instituicaoRepository.deleteAll();
			contaRepository.deleteAll();
			categoriaRepository.deleteAll();

			Usuario u = new Usuario();
			Usuario acessor = new Usuario();
			Categoria ca = new Categoria();
			Transacao t = new Transacao();
			Instituicao i = new Instituicao();
			Conta co = new Conta();

			u.setUsername("victor");
			u.setPassword("victor");
			u.setNomeCompleto("victor");
			u.setEmail("victor@gmail.com");
			u.setTelefone("4002-8922");
			u.setChavePix("4002-8922");

			usuarioRepository.save(u);

			acessor.setUsername("gustavo");
			acessor.setPassword("gustavo");
			acessor.setNomeCompleto("gustavo");
			acessor.setEmail("gustavo@gmail.com");
			acessor.setTelefone("4002-8922");
			acessor.setChavePix("4002-8922");

			u.setAcessor(acessor);
			acessor.addCliente(u);
			usuarioRepository.save(acessor);
			usuarioRepository.save(u);

			ca.setNome("Transporte");
			ca.setValorPlanejado(100.00);

			ca.setUsuario(u);
			u.addCategoria(ca);
			categoriaRepository.save(ca);

			t.setDescricao("Compra de café");
			t.setValor(5.50);
			t.setDataTransacao(new Date());
			t.setTipo(TipoTransacao.DEBITO);
			t.setParcelas(1);

			t.setUsuario(u);
			u.addTransacao(t);
			t.setCategoria(ca);
			ca.addTransacao(t);
			transacaoRepository.save(t);

			i.setNome("Caixa");

			i.setUsuario(u);
			u.addCarteira(i);

			co.setNome("Credito");
			co.setValorConta(1000.0);
			co.setTipo(TipoTransacao.CREDITO);

			co.setInstituicao(i);
			i.addConta(co);
			co.addTransacao(t);
			t.setConta(co);
			instituicaoRepository.save(i);
			contaRepository.save(co);
		};
	}
}
