package com.victor.dto;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CategoriaDTO(
    UUID id,
    @NotBlank @Length(max = 50) String nome,
    @NotNull @PositiveOrZero Double valorPlanejado) {

}
