package br.teste.tecnico.AID.Teste.Tecnico.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.teste.tecnico.AID.Teste.Tecnico.exceptions.DataInvalidaException;
import br.teste.tecnico.AID.Teste.Tecnico.mapper.AgendamentoMapper;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.AgendamentoRequest;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.AgendamentoResponse;
import br.teste.tecnico.AID.Teste.Tecnico.model.entities.Agendamento;
import br.teste.tecnico.AID.Teste.Tecnico.model.entities.Status;
import br.teste.tecnico.AID.Teste.Tecnico.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

    private AgendamentoMapper agendamentoMapper;
    private AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoMapper agendamentoMapper, AgendamentoRepository agendamentoRepository) {
        this.agendamentoMapper = agendamentoMapper;
        this.agendamentoRepository = agendamentoRepository;
    }

    public AgendamentoResponse criarAgendamento(AgendamentoRequest agendamentoRequest) {
        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoRequest);
        if (agendamento.getFim().isBefore(agendamento.getInicio()))
            throw new DataInvalidaException("A data de Fim é menor do que a data de início!");
        agendamentoRepository.save(agendamento);
        return agendamentoMapper.toResponse(agendamento);
    }

    public List<AgendamentoResponse> listarAgendamentoStatus(String status) {
        Status statusEnum = Status.valueOf(status.toUpperCase());
        List<Agendamento> agendamentos = agendamentoRepository.findByStatus(statusEnum);
        List<AgendamentoResponse> agendamentoResponses = agendamentos.stream().map(x -> agendamentoMapper.toResponse(x))
                .toList();
        return agendamentoResponses;
    }
}
