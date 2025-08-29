package com.victor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "categorias")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Categoria {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotBlank
  @Length(max = 50)
  @Column(length = 50, nullable = false)
  private String nome;

  @NotNull
  @PositiveOrZero
  @Column(nullable = false)
  private Double valorPlanejado;

  @NotNull
  @Valid
  @ManyToOne(optional = false)
  @JoinColumn(name = "idUsuario", nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Usuario usuario;

  @NotNull
  @Valid
  @OneToMany(mappedBy = "categoria")
  private List<Transacao> transacoes = new ArrayList<>();

  public UUID getId() {
    return id;
  }

  public void setId(UUID idCategoria) {
    this.id = idCategoria;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(@NotBlank @Length(max = 50) String titulo) {
    this.nome = titulo;
  }

  public Double getValorPlanejado() {
    return valorPlanejado;
  }

  public void setValorPlanejado(@NotNull @PositiveOrZero Double valorPlanejado) {
    this.valorPlanejado = valorPlanejado;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(@NotNull @Valid Usuario usuario) {
    this.usuario = usuario;
  }

  public List<Transacao> getTransacoes() {
    return new ArrayList<Transacao>(this.transacoes);
  }

  public void addTransacao(@NotNull @Valid Transacao transacao) {
    this.transacoes.add(transacao);
  }

  public void limparTransacoes() {
    this.transacoes.clear();
  }
}
