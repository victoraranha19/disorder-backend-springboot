package com.victor.model;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

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

  @Column()
  private int idAcessor;

  @NotNull
  @Column(nullable = false)
  private boolean ativo;
}
