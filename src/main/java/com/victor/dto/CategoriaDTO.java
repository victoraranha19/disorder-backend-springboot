package com.victor.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CategoriaDTO(Long id,
    @NotBlank @Length(max = 100) String titulo,
    @NotNull @PositiveOrZero Double valorPlanejado) {

}
