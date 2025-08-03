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
@Table(name = "carteiras")
public class Carteira {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 255, nullable = false)
  private String titulo;

  @Column()
  private double contaCorrente;
  @Column()
  private double contaPoupanca;
  @Column()
  private double contaInvestimento;

  @Column()
  private double limiteCreditoTotal;

  @Column(nullable = false)
  private int idUsuario;

  @Column(nullable = false)
  private boolean ativo;
}
