package com.victor.dto;

import com.victor.enums.TipoCarteira;
import com.victor.enums.validation.ValueOfEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import org.jspecify.annotations.Nullable;

import java.util.Date;

public record TransacaoDTO(
        Integer id,
        @NotNull @Length(max = 255) String descricao,
        @NotNull @Positive Double valor,
        @NotNull Date dataTransacao,
        @NotNull @ValueOfEnum(enumClass = TipoCarteira.class) String tipo,
        @NotNull @Positive Integer parcelas,
        @Nullable Integer idCarteira,
        @NotNull String tituloCarteira,
        @Nullable Integer idCategoria,
        @NotNull String tituloCategoria){
}
