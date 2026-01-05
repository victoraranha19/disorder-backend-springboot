package com.victor.enums.converters;

import com.victor.enums.PapelAcesso;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PapelAcessoConverter implements AttributeConverter<PapelAcesso, String> {

    @Override
    public String convertToDatabaseColumn(PapelAcesso papelAcesso) {
        if (papelAcesso == null) {
            return null;
        }
        return papelAcesso.getValue();
    }

    @Override
    public PapelAcesso convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Stream.of(PapelAcesso.values())
                .filter(p -> p.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
