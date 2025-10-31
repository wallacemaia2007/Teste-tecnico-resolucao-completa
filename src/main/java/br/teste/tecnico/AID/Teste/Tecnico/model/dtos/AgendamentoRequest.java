package br.teste.tecnico.AID.Teste.Tecnico.model.dtos;

import java.time.LocalDateTime;
import java.util.List;

import br.teste.tecnico.AID.Teste.Tecnico.model.enums.Status;

public record AgendamentoRequest(String pacienteCpf, String medicoCrm,String especialidade,
LocalDateTime inicio, LocalDateTime fim,Status status, LocalDateTime dataCriacao, List<String> notas ) {
    
}
