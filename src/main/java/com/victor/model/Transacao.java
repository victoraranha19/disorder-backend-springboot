package com.victor.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transacoes")
public class Transacao {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 255)
  private String descricao;

  @Column(nullable = false)
  private double valor;

  @Column(nullable = false)
  private Date dataTransacao;

  @Column(nullable = false)
  private char tipo; // 'C' para crédito, 'D' para débito

  private int idCategoria;

  private int idCarteira;

  @Column(nullable = false)
  private int idUsuario;

  @Column(nullable = false)
  private boolean ativo;
}
