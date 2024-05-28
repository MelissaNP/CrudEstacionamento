package br.com.ada.estacionamento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class VagaOcupadaException extends RuntimeException {
    
    public VagaOcupadaException(String message) {
        super(message);
    }
    
}
