package com.victor.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CarteiraDTO(
    Long id,
    @NotBlank @Length(max = 100) String titulo,
    @NotNull @PositiveOrZero Double contaCorrente,
    @NotNull @PositiveOrZero Double contaPoupanca,
    @NotNull @PositiveOrZero Double contaInvestimento,
    @NotNull @PositiveOrZero Double limiteCreditoTotal) {

}
