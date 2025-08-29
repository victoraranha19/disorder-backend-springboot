package com.victor.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record InstituicaoDTO(
        Long id,
        @NotBlank @Length(max = 100) String nome,
        @NotEmpty @Valid List<ContaDTO> contas) {

}
