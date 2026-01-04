package com.victor.exception;

import java.io.Serial;
import java.util.UUID;

public class RecordNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Integer id) {
        super("Registro não encontrado. ID: " + id);
    }

    public RecordNotFoundException(UUID id) {
        super("Registro não encontrado. UUID: " + id);
    }
}
