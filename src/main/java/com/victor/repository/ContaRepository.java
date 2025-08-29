package com.victor.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

}
