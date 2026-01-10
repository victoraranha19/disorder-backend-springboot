package com.victor.enums;

public enum TipoTransacao {
    ENTRADA("E"), SAIDA("S");

    private final String value;

    TipoTransacao(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
