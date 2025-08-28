package com.victor.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
@SoftDelete(strategy = SoftDeleteType.ACTIVE, columnName = "ativo")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idUsuario;

  @NotBlank
  @Length(max = 30)
  @Column(length = 30, unique = true, nullable = false)
  private String username;

  @NotBlank
  @Length(max = 30)
  @Column(length = 30, nullable = false)
  private String password;

  @NotBlank
  @Length(min = 3, max = 100)
  @Column(length = 100, nullable = false)
  private String nomeCompleto;

  @NotBlank
  @Length(max = 100)
  @Email
  @Column(length = 100, unique = true, nullable = false)
  private String email;

  @Length(max = 20)
  @Column(length = 20)
  private String telefone;

  @Length(max = 255)
  @Column(length = 255)
  private String chavePix;

  @ManyToOne(optional = true)
  @JoinColumn(name = "idAcessor")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Usuario acessor;

  @OneToMany(mappedBy = "acessor", fetch = FetchType.LAZY)
  private List<Usuario> clientes = new ArrayList<Usuario>();

  @OneToMany(mappedBy = "usuario", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Transacao> transacoes = new ArrayList<Transacao>();

  @OneToMany(mappedBy = "usuario", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Instituicao> carteiras = new ArrayList<Instituicao>();

  @OneToMany(mappedBy = "usuario", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Categoria> categorias = new ArrayList<Categoria>();

  public Long getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(Long idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(@NotBlank @Length(max = 30) String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(@NotBlank @Length(max = 30) String password) {
    this.password = password;
  }

  public String getNomeCompleto() {
    return nomeCompleto;
  }

  public void setNomeCompleto(@NotBlank @Length(min = 3, max = 100) String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(@NotBlank @Length(max = 100) @Email String email) {
    this.email = email;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(@Length(max = 20) String telefone) {
    this.telefone = telefone;
  }

  public String getChavePix() {
    return chavePix;
  }

  public void setChavePix(@Length(max = 255) String chavePix) {
    this.chavePix = chavePix;
  }

  public Usuario getAcessor() {
    return this.acessor;
  }

  public void setAcessor(Usuario acessor) {
    this.acessor = acessor;
  }

  public List<Usuario> getClientes() {
    return new ArrayList<Usuario>(this.clientes);
  }

  public void addCliente(Usuario cliente) {
    this.clientes.add(cliente);
  }

  public void limparClientes() {
    this.clientes.clear();
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

  public List<Instituicao> getCarteiras() {
    return new ArrayList<Instituicao>(this.carteiras);
  }

  public void addCarteira(Instituicao carteira) {
    this.carteiras.add(carteira);
  }

  public void limparCarteiras() {
    this.carteiras.clear();
  }

  public List<Categoria> getCategorias() {
    return new ArrayList<Categoria>(this.categorias);
  }

  public void addCategoria(Categoria categoria) {
    this.categorias.add(categoria);
  }

  public void limparCategorias() {
    this.categorias.clear();
  }
}
