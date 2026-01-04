package com.victor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DisorderSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisorderSpringApplication.class, args);
    }

//    @Bean
//    CommandLineRunner initDataBase(UsuarioRepository usuarioRepository, TransacaoRepository transacaoRepository,
//                                   CarteiraRepository carteiraRepository, CategoriaRepository categoriaRepository) {
//        return args -> {
//            usuarioRepository.deleteAll();
//            transacaoRepository.deleteAll();
//            carteiraRepository.deleteAll();
//            categoriaRepository.deleteAll();
//
//            Usuario u = new Usuario();
//            Usuario acessor = new Usuario();
//            Categoria ca = new Categoria();
//            Transacao t = new Transacao();
//
//            u.setUsername("victor");
//            u.setSenha("victor");
//            u.setNomeCompleto("victor");
//            u.setEmail("victor@gmail.com");
//            u.setTelefone("4002-8922");
//            u.setChavePix("4002-8922");
//            u.setPapel("");
//
//            usuarioRepository.save(u);
//
//            acessor.setUsername("gustavo");
//            acessor.setSenha("gustavo");
//            acessor.setNomeCompleto("gustavo");
//            acessor.setEmail("gustavo@gmail.com");
//            acessor.setTelefone("4002-8922");
//            acessor.setChavePix("4002-8922");
//            acessor.setPapel("acessor");
//
//            u.setAcessor(acessor);
//            acessor.addCliente(u);
//            usuarioRepository.save(acessor);
//            usuarioRepository.save(u);
//
//            ca.setTitulo("Transporte");
//            ca.setValorPlanejado(100.00);
//
//            ca.setUsuario(u);
//            u.addCategoria(ca);
//            categoriaRepository.save(ca);
//
//            t.setDescricao("Compra de café");
//            t.setValor(5.50);
//            t.setDataTransacao(new Date());
//            t.setTipo(TipoTransacao.DEBITO);
//            t.setParcelas(1);
//
//            t.setUsuario(u);
//            u.addTransacao(t);
//            t.setCategoria(ca);
//            ca.addTransacao(t);
//            transacaoRepository.save(t);
//        };
//    }
}
