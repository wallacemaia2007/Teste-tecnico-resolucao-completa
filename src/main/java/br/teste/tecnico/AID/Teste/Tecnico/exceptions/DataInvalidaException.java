package br.teste.tecnico.AID.Teste.Tecnico.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataInvalidaException extends RuntimeException {

    public DataInvalidaException(String msg){
        super(msg);
    }
    
}
