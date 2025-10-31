package br.teste.tecnico.AID.Teste.Tecnico.model.dtos;

import java.time.LocalDateTime;

public record ErroResponse(LocalDateTime timestamp,String message,String details) {}