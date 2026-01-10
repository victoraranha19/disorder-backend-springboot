package com.victor.enums;

public enum TipoCarteira {
    CONTA_CORRENTE("CC"),CONTA_POUPANCA("CP"),CONTA_INVESTIMENTO("CI"), LIMITE_CREDITO("LC");

    private final String value;

    TipoCarteira(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static TipoCarteira convertTipoTransacaoValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "CC" -> TipoCarteira.CONTA_CORRENTE;
            case "CP" -> TipoCarteira.CONTA_POUPANCA;
            case "CI" -> TipoCarteira.CONTA_INVESTIMENTO;
            case "LC" -> TipoCarteira.LIMITE_CREDITO;
            default -> throw new IllegalArgumentException("Tipo de Transação inválido: " + value);
        };
    }
}
