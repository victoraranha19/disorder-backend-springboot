package com.victor.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TransacaoDTO(
        Long id,
        @NotNull @Length(max = 255) String descricao,
        @NotNull @PositiveOrZero Double valor,
        @NotNull Date dataTransacao,
        @NotNull String tipo,
        Integer idCategoria,
        Integer idCarteira) {

}
