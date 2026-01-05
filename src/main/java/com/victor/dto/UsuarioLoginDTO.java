package com.victor.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioLoginDTO(
        @NotBlank @Length(max = 30) String login,
        @NotBlank String senha) {
}
