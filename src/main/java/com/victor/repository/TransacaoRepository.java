package com.victor.repository;

import com.victor.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    Page<Transacao> findByUsuarioId(UUID idUsuario, Pageable pageable);
}
