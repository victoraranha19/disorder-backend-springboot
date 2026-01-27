package com.victor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UsuarioDTO(
        @NotBlank @Length(min = 3, max = 100) String nomeCompleto,
        @NotBlank @Length(max = 100) @Email String email,
        @NotNull @Length(max = 20) String telefone,
        @NotNull @Length(max = 255) String chavePix) {
}
