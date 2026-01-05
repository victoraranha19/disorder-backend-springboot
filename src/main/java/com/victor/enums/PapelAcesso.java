package com.victor.enums;

public enum PapelAcesso {
    ADMIN("ADMIN"), ACESSOR("ACESSOR"), USER("USR");

    private final String value;

    PapelAcesso(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static PapelAcesso convertPapelAcessoValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "ADMIN" -> PapelAcesso.ADMIN;
            case "ACESSOR" -> PapelAcesso.ACESSOR;
            case "USER" -> PapelAcesso.USER;
            default -> throw new IllegalArgumentException("Tipo de Transação inválido: " + value);
        };
    }
}
