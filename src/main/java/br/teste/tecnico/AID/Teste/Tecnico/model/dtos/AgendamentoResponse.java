package br.teste.tecnico.AID.Teste.Tecnico.model.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.teste.tecnico.AID.Teste.Tecnico.model.enums.Status;

public record AgendamentoResponse(UUID id , String pacienteCpf, String medicoCrm,String especialidade,
LocalDateTime inicio, LocalDateTime fim,Status status, LocalDateTime dataCriacao, List<String> notas ) {
    
}
