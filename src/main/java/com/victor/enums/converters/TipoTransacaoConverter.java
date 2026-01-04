package com.victor.enums.converters;

import com.victor.enums.TipoTransacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TipoTransacaoConverter implements AttributeConverter<TipoTransacao, String> {

    @Override
    public String convertToDatabaseColumn(TipoTransacao tipoTransacao) {
        if (tipoTransacao == null) {
            return null;
        }
        return tipoTransacao.getValue();
    }

    @Override
    public TipoTransacao convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(TipoTransacao.values())
                .filter(t -> t.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
