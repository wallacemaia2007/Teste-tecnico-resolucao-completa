package br.teste.tecnico.AID.Teste.Tecnico.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.teste.tecnico.AID.Teste.Tecnico.exceptions.DataInvalidaException;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.ErroResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataInvalidaException.class)
	public ResponseEntity<ErroResponse> handleAcessoNegadoException(DataInvalidaException ex,
			WebRequest request) {
		ErroResponse erroResponse = new ErroResponse(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(erroResponse, HttpStatus.BAD_REQUEST);
	}


    
}
