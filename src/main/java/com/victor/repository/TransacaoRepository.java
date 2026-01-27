package com.victor.repository;

import com.victor.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    List<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndCategoriaIdAndValorGreaterThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCarteira, Integer idCategoria, Double valor);

    List<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndCategoriaIdAndValorLessThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCarteira, Integer idCategoria, Double valor);

    List<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndValorGreaterThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCarteira, Double valor);

    List<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndValorLessThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCarteira, Double valor);

    List<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCategoriaIdAndValorGreaterThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCategoria, Double valor);

    List<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCategoriaIdAndValorLessThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCategoria, Double valor);

    List<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndValorGreaterThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Double valor);

    List<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndValorLessThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Double valor);
}
