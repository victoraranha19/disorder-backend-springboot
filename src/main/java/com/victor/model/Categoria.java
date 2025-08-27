package com.victor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 255, nullable = false)
  private String titulo;

  @Column()
  private double valorPlanejado;

  @Column(nullable = false)
  private int idUsuario;

  @Column(nullable = false)
  private boolean ativo;
}
