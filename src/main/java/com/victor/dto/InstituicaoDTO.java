package com.victor.dto;

import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record InstituicaoDTO(
    UUID id,
    @NotBlank @Length(max = 50) String nome,
    @NotEmpty @Valid List<ContaDTO> contas) {

}
