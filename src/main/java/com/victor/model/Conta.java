package com.victor.model;

import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "contas")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Conta {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idConta;

  @NotBlank
  @Length(max = 100)
  @Column(length = 100, nullable = false)
  private String nome;

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private Double valorConta = 0.0;

  @NotNull
  @Column(nullable = false)
  @Convert(converter = TipoTransacaoConverter.class)
  private TipoTransacao tipoTransacao; // 'C' para crédito, 'D' para débito

  @ManyToOne(optional = false)
  @JoinColumn(name = "idInstituicao", nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Instituicao instituicao;

  @OneToMany(mappedBy = "conta")
  private List<Transacao> transacoes = new ArrayList<>();

  public Long getIdConta() {
    return idConta;
  }

  public void setIdConta(Long idConta) {
    this.idConta = idConta;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(@NotBlank @Length(max = 100) String nome) {
    this.nome = nome;
  }

  public Double getValorConta() {
    return valorConta;
  }

  public void setValorConta(@NotNull @PositiveOrZero Double valorConta) {
    this.valorConta = valorConta;
  }

  public TipoTransacao getTipoTransacao() {
    return tipoTransacao;
  }

  public void setTipoTransacao(@NotNull TipoTransacao tipoTransacao) {
    this.tipoTransacao = tipoTransacao;
  }

  public Instituicao getInstituicao() {
    return instituicao;
  }

  public void setInstituicao(Instituicao instituicao) {
    this.instituicao = instituicao;
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
