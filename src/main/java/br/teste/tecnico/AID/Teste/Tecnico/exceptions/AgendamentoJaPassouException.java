package br.teste.tecnico.AID.Teste.Tecnico.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AgendamentoJaPassouException extends RuntimeException {

    public AgendamentoJaPassouException(String msg){
        super(msg);
    }


    
}
