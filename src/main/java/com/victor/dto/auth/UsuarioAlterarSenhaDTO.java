package com.victor.dto.auth;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioAlterarSenhaDTO(
        @NotBlank String senhaAntiga,
        @NotBlank @Length(min = 3, max = 30) String senhaNova) {
}
