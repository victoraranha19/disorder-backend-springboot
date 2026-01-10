package com.victor.enums.converters;

import com.victor.enums.TipoCarteira;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TipoCarteiraConverter implements AttributeConverter<TipoCarteira, String> {

    @Override
    public String convertToDatabaseColumn(TipoCarteira tipoTransacao) {
        if (tipoTransacao == null) return null;
        return tipoTransacao.getValue();
    }

    @Override
    public TipoCarteira convertToEntityAttribute(String value) {
        if (value == null) return null;
        return Stream.of(TipoCarteira.values())
                .filter(t -> t.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
