package br.com.ada.estacionamento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CarroCadastradoException extends RuntimeException {
    
    public CarroCadastradoException(String message) {
        super(message);
    }
}
