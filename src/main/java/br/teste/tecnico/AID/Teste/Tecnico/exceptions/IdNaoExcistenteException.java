package br.teste.tecnico.AID.Teste.Tecnico.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNaoExcistenteException extends RuntimeException {

    public IdNaoExcistenteException(String msg){
        super(msg);
    }
    
}
