package com.victor.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioEsqueciSenhaDTO(@NotBlank @Email String email) {
}
