package com.victor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 30, unique = true, nullable = false)
  private String username;

  @Column(length = 30, nullable = false)
  private String password;

  @Column(length = 100, nullable = false)
  private String nomeCompleto;

  @Column(length = 100, nullable = false)
  private String email;

  @Column(length = 100, nullable = false)
  private String telefone;

  @Column(length = 100, nullable = false)
  private String chavePix;

  @Column()
  private int idAcessor;

  @Column(nullable = false)
  private boolean ativo;
}
