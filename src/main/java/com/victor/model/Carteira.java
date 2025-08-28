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

  public Long getIdCarteira() {
    return idCarteira;
  }

  public void setIdCarteira(Long idCarteira) {
    this.idCarteira = idCarteira;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(@NotBlank @Length(max = 100) String titulo) {
    this.titulo = titulo;
  }

  public Double getContaCorrente() {
    return contaCorrente;
  }

  public void setContaCorrente(@NotNull @PositiveOrZero Double contaCorrente) {
    this.contaCorrente = contaCorrente;
  }

  public Double getContaPoupanca() {
    return contaPoupanca;
  }

  public void setContaPoupanca(@NotNull @PositiveOrZero Double contaPoupanca) {
    this.contaPoupanca = contaPoupanca;
  }

  public Double getContaInvestimento() {
    return contaInvestimento;
  }

  public void setContaInvestimento(@NotNull @PositiveOrZero Double contaInvestimento) {
    this.contaInvestimento = contaInvestimento;
  }

  public Double getLimiteCreditoTotal() {
    return limiteCreditoTotal;
  }

  public void setLimiteCreditoTotal(@NotNull @PositiveOrZero Double limiteCreditoTotal) {
    this.limiteCreditoTotal = limiteCreditoTotal;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public List<Transacao> getTransacoes() {
    return new ArrayList<Transacao>(this.transacoes);
  }

  public void addTransacao(Transacao transacao) {
    this.transacoes.add(transacao);
  }

  public void limparTransacoes() {
    this.transacoes.clear();
  }
}
