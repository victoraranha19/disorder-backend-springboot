package com.victor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

public record CarteiraDTO(
        Integer id,
        @NotBlank @Length(max = 50) String titulo,
        @NotNull @PositiveOrZero Double contaCorrente,
        @NotNull @PositiveOrZero Double contaPoupanca,
        @NotNull @PositiveOrZero Double contaInvestimento,
        @NotNull @PositiveOrZero Double limiteCreditoTotal) {

}
