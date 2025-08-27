package com.victor.model;

import java.util.Date;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
@Table(name = "transacoes")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
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
  private Double valor;

  @NotNull
  @Column(nullable = false)
  private Date dataTransacao;

  @NotNull
  @Pattern(regexp = "C|D")
  @Column(nullable = false)
  private String tipo; // 'C' para crédito, 'D' para débito

  @Column()
  private Integer idCategoria;

  @Column()
  private Integer idCarteira;

  @NotNull
  @Column(nullable = false)
  private Integer idUsuario;
}
