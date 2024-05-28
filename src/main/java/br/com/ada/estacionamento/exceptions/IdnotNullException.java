package br.com.ada.estacionamento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IdnotNullException extends RuntimeException{

 public IdnotNullException(String message) {
        super(message);
    };
 


    
}