package com.victor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioRegistroDTO(
        @NotBlank @Length(max = 30) String login,
        @NotBlank String senha,
        @NotBlank @Length(min = 3, max = 100) String nomeCompleto,
        @NotBlank @Length(max = 100) @Email String email) {
}
