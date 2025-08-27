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
@Table(name = "categorias")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Categoria {

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
  private double valorPlanejado;

  @NotNull
  @Column(nullable = false)
  private int idUsuario;
}
