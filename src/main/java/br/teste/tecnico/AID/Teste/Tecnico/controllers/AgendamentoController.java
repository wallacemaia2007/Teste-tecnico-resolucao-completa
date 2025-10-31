package br.teste.tecnico.AID.Teste.Tecnico.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.AgendamentoRequest;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.AgendamentoResponse;
import br.teste.tecnico.AID.Teste.Tecnico.services.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping()
    public ResponseEntity<AgendamentoResponse> criarAgendamento(@RequestBody AgendamentoRequest agendamentoRequest) {
        AgendamentoResponse agendamentoResponse = agendamentoService.criarAgendamento(agendamentoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoResponse);
    }

}
