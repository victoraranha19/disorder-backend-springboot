package com.victor.repository;

import com.victor.model.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {
    List<Carteira> findByUsuarioId(UUID idUsuario);
}
