package com.victor.enums;

public enum TipoTransacao {
  DEBITO("D"), CREDITO("C");

  private String value;

  private TipoTransacao(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }

  public static TipoTransacao convertTipoTransacaoValue(String value) {
    if (value == null) {
      return null;
    }
    return switch (value) {
      case "D" -> TipoTransacao.DEBITO;
      case "C" -> TipoTransacao.CREDITO;
      default -> throw new IllegalArgumentException("Tipo de Transação inválido: " + value);
    };
  }
}
