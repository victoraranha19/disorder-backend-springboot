package com.victor.model;

import java.util.Date;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.victor.enums.TipoTransacao;
import com.victor.enums.converters.TipoTransacaoConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Entity
@Table(name = "transacoes")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Transacao {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idTransacao;

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
  @Column(nullable = false)
  @Convert(converter = TipoTransacaoConverter.class)
  private TipoTransacao tipo; // 'C' para crédito, 'D' para débito

  @ManyToOne(optional = false)
  @JoinColumn(name = "idUsuario", nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Usuario usuario;

  @ManyToOne(optional = true)
  @JoinColumn(name = "idCarteira")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Carteira carteira;

  @ManyToOne(optional = true)
  @JoinColumn(name = "idCategoria")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Categoria categoria;
}
