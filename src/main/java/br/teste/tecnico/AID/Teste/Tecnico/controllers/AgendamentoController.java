package br.teste.tecnico.AID.Teste.Tecnico.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{status}")
    public ResponseEntity<List<AgendamentoResponse>> listarAgendamentoStatus(@PathVariable String status) {
        List<AgendamentoResponse> agendamentoResponses = agendamentoService.listarAgendamentoStatus(status);
        return ResponseEntity.ok().body(agendamentoResponses);
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> listarTodosAgendamentos() {
        List<AgendamentoResponse> agendamentoResponses = agendamentoService.listarTodosAgendamentos();
        return ResponseEntity.ok().body(agendamentoResponses);
    }

}
