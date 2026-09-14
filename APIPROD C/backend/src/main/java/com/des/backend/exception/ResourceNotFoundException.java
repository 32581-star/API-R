package com.des.backend.exception;

public class ResourceNotFoundException extends
        RuntimeException {

    public ResourceNotFoundException(String message) {
        // Guarda a mensagem que o handler vai ler
        // com o getMessage()
        super(message);
    }
}
