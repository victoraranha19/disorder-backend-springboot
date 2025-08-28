package com.victor.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
  private Long idCarteira;

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

  @ManyToOne(optional = false)
  @JoinColumn(name = "idUsuario", nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Usuario usuario;

  @OneToMany(mappedBy = "carteira")
  private List<Transacao> transacoes = new ArrayList<>();
}
