package com.victor.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.model.Instituicao;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, UUID> {

}
