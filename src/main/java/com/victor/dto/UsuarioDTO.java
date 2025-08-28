package com.victor.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
    Long id,
    @NotBlank @Length(max = 30) String username,
    @NotBlank @Length(min = 3, max = 100) String nomeCompleto,
    @NotBlank @Length(max = 100) @Email String email,
    @Length(max = 20) String telefone,
    @Length(max = 255) String chavePix,
    List<CarteiraDTO> carteiras,
    List<CategoriaDTO> categorias,
    List<TransacaoDTO> transacoes,
    Integer idAcessor) {

}
