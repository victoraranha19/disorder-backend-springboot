package com.victor.exception;

import java.util.UUID;

public class RecordNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public RecordNotFoundException(Long id) {
    super("Registro não encontrado. ID: " + id);
  }

  public RecordNotFoundException(UUID id) {
    super("Registro não encontrado. UUID: " + id);
  }
}
