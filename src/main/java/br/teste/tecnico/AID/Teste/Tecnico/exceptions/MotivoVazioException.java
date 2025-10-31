package br.teste.tecnico.AID.Teste.Tecnico.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MotivoVazioException extends RuntimeException {

    public MotivoVazioException(String msg){
        super(msg);
    }
    
}

