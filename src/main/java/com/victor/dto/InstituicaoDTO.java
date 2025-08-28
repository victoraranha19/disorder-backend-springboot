package com.victor.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

public record InstituicaoDTO(Long id,
        @NotBlank @Length(max = 100) String nome,
        List<ContaDTO> contas) {

}
