package com.victor.dto;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.victor.enums.TipoTransacao;
import com.victor.enums.validation.ValueOfEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransacaoDTO(
    UUID id,
    @NotNull @Length(max = 255) String descricao,
    @NotNull @Positive Double valor,
    @NotNull Date dataTransacao,
    @NotNull @ValueOfEnum(enumClass = TipoTransacao.class) String tipo,
    @NotNull @Positive Integer parcelas) {
}
