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

@Entity
@Table(name = "instituicoes")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Instituicao {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idInstituicao;

  @NotBlank
  @Length(max = 100)
  @Column(length = 100, nullable = false)
  private String nome;

  @ManyToOne(optional = false)
  @JoinColumn(name = "idUsuario", nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Usuario usuario;

  @OneToMany(mappedBy = "instituicao")
  private List<Conta> contas = new ArrayList<Conta>();

  public Long getIdInstituicao() {
    return idInstituicao;
  }

  public void setIdInstituicao(Long idCarteira) {
    this.idInstituicao = idCarteira;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(@NotBlank @Length(max = 100) String titulo) {
    this.nome = titulo;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public List<Conta> getContas() {
    return new ArrayList<Conta>(this.contas);
  }

  public void addConta(Conta conta) {
    this.contas.add(conta);
  }

  public void limparContas() {
    this.contas.clear();
  }
}
