package br.teste.tecnico.AID.Teste.Tecnico.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.teste.tecnico.AID.Teste.Tecnico.exceptions.AgendamentoJaCanceladoException;
import br.teste.tecnico.AID.Teste.Tecnico.exceptions.AgendamentoJaPassouException;
import br.teste.tecnico.AID.Teste.Tecnico.exceptions.DataInvalidaException;
import br.teste.tecnico.AID.Teste.Tecnico.exceptions.IdNaoExcistenteException;
import br.teste.tecnico.AID.Teste.Tecnico.exceptions.IdNuloException;
import br.teste.tecnico.AID.Teste.Tecnico.exceptions.MotivoVazioException;
import br.teste.tecnico.AID.Teste.Tecnico.mapper.AgendamentoMapper;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.AgendamentoRequest;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.AgendamentoResponse;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.CancelamentoRequest;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.NotaRequest;
import br.teste.tecnico.AID.Teste.Tecnico.model.entities.Agendamento;
import br.teste.tecnico.AID.Teste.Tecnico.model.enums.Status;
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
        List<Agendamento> agendamentos = agendamentoRepository.findAllByStatus(statusEnum);
        List<AgendamentoResponse> agendamentoResponses = agendamentos.stream().map(x -> agendamentoMapper.toResponse(x))
                .toList();
        return agendamentoResponses;
    }

    public List<AgendamentoResponse> listarTodosAgendamentos() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        List<AgendamentoResponse> agendamentoResponses = agendamentos.stream().map(x -> agendamentoMapper.toResponse(x))
                .toList();
        return agendamentoResponses;
    }

    public AgendamentoResponse adicionarNota(UUID id, NotaRequest notaRequest) {
        Agendamento agendamento = verificarAgendamentoRepo(id);
        agendamento.getNotas().add(notaRequest.descricao());
        agendamentoRepository.save(agendamento);
        AgendamentoResponse agendamentoResponse = agendamentoMapper.toResponse(agendamento);

        return agendamentoResponse;
    }

    public AgendamentoResponse cancelarAgendamento(UUID id, CancelamentoRequest cancelamentoRequest) {
        Agendamento agendamento = verificarAgendamentoRepo(id);

        if (agendamento.getInicio().isBefore(LocalDateTime.now())) {
            throw new AgendamentoJaPassouException("O agendamento indicado já passou da data! ");
        }
        if (!StringUtils.hasText(cancelamentoRequest.motivo()))
            throw new MotivoVazioException("O motivo para o cancelamento não pode ficar vazio!");
        else {
            if (agendamento.getStatus() == Status.CANCELADO) {
                throw new AgendamentoJaCanceladoException("O Agendamento mencionado já foi cancelado!");
            } else {
                agendamento.setStatus(Status.CANCELADO);
                agendamentoRepository.save(agendamento);
            }
            AgendamentoResponse agendamentoResponse = agendamentoMapper.toResponse(agendamento);

            return agendamentoResponse;
        }

    }

    public Agendamento verificarAgendamentoRepo(UUID id) {
        if (id.equals(null))
            throw new IdNuloException("O id não pode ser nulo!");
        return agendamentoRepository.findById(id).orElseThrow(
                () -> new IdNaoExcistenteException("O id mencionado não existe!"));
    }
}
