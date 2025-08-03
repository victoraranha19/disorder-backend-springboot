package com.victor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.model.Carteira;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

}
