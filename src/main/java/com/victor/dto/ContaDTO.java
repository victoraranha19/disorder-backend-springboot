package com.victor.dto;

import org.hibernate.validator.constraints.Length;

import com.victor.enums.TipoTransacao;
import com.victor.enums.validation.ValueOfEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ContaDTO(
    Long id,
    @NotBlank @Length(max = 100) String nome,
    @NotNull @PositiveOrZero Double valorConta,
    @NotNull @ValueOfEnum(enumClass = TipoTransacao.class) String tipo) {

}
