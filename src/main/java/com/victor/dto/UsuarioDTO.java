package com.victor.dto;

import com.victor.enums.PapelAcesso;
import com.victor.enums.validation.ValueOfEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record UsuarioDTO(
        UUID id,
        @NotBlank @Length(max = 30) String login,
        @NotBlank @Length(min = 3, max = 100) String nomeCompleto,
        @NotBlank @Length(max = 100) @Email String email,
        @NotNull @Length(max = 20) String telefone,
        @NotNull @Length(max = 255) String chavePix,
        @NotNull @ValueOfEnum(enumClass = PapelAcesso.class) String papel) {

}
