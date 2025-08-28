package com.victor.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.victor.enums.TipoTransacao;
import com.victor.enums.validation.ValueOfEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record TransacaoDTO(
        Long id,
        @NotNull @Length(max = 255) String descricao,
        @NotNull @PositiveOrZero Double valor,
        @NotNull Date dataTransacao,
        @NotNull @ValueOfEnum(enumClass = TipoTransacao.class) String tipo,
        @NotNull @Positive Integer parcelas) {
}
