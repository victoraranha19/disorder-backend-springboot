package com.victor.model;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
@Table(name = "carteiras")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Carteira {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank
  @Length(max = 100)
  @Column(length = 100, nullable = false)
  private String titulo;

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private Double contaCorrente;

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private Double contaPoupanca;

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private Double contaInvestimento;

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private Double limiteCreditoTotal;

  @NotNull
  @Column(nullable = false)
  private Integer idUsuario;
}
