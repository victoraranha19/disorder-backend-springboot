package com.victor.repository;

import com.victor.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    Page<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndCategoriaIdAndValorGreaterThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCarteira, Integer idCategoria, Double valor, Pageable pageable);

    Page<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndCategoriaIdAndValorLessThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCarteira, Integer idCategoria, Double valor, Pageable pageable);

    Page<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndValorGreaterThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCarteira, Double valor, Pageable pageable);

    Page<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCarteiraIdAndValorLessThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCarteira, Double valor, Pageable pageable);

    Page<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCategoriaIdAndValorGreaterThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCategoria, Double valor, Pageable pageable);

    Page<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndCategoriaIdAndValorLessThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Integer idCategoria, Double valor, Pageable pageable);

    Page<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndValorGreaterThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Double valor, Pageable pageable);

    Page<Transacao> findByUsuarioIdAndDataTransacaoBetweenAndValorLessThanOrderByDataTransacaoDesc(UUID idUsuario, Date dataInicio, Date dataFim, Double valor, Pageable pageable);
}
