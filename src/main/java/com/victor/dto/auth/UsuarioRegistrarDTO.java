package com.victor.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioRegistrarDTO(
        @NotBlank @Length(min = 3, max = 30) String senha,
        @NotBlank @Length(min = 3, max = 100) String nomeCompleto,
        @NotBlank @Length(min = 3, max = 100) @Email String email) {
}
