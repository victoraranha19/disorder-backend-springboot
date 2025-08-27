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
}
