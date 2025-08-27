package com.victor.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
@Table(name = "transacoes")
public class Transacao {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Length(max = 255)
  @Column(length = 255, nullable = false)
  private String descricao;

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private double valor;

  @NotNull
  @Column(nullable = false)
  private Date dataTransacao;

  @NotNull
  @Column(nullable = false)
  private char tipo; // 'C' para crédito, 'D' para débito

  @Column()
  private int idCategoria;

  @Column()
  private int idCarteira;

  @NotNull
  @Column(nullable = false)
  private int idUsuario;

  @NotNull
  @Column(nullable = false)
  private boolean ativo;
}
