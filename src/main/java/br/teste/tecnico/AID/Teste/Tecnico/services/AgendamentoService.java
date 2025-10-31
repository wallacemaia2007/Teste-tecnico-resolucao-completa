package br.teste.tecnico.AID.Teste.Tecnico.services;

import org.springframework.stereotype.Service;

import br.teste.tecnico.AID.Teste.Tecnico.exceptions.DataInvalidaException;
import br.teste.tecnico.AID.Teste.Tecnico.mapper.AgendamentoMapper;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.AgendamentoRequest;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.AgendamentoResponse;
import br.teste.tecnico.AID.Teste.Tecnico.model.entities.Agendamento;
import br.teste.tecnico.AID.Teste.Tecnico.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

    private AgendamentoMapper agendamentoMapper;
    private AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoMapper agendamentoMapper, AgendamentoRepository agendamentoRepository) {
        this.agendamentoMapper = agendamentoMapper;
        this.agendamentoRepository = agendamentoRepository;
    }



    public AgendamentoResponse criarAgendamento(AgendamentoRequest agendamentoRequest){
        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoRequest);
        if(agendamento.getFim().isBefore(agendamento.getInicio())){
            throw new DataInvalidaException("A data de Fim é menor do que a data de início!");
        }
        agendamentoRepository.save(agendamento);
        return agendamentoMapper.toResponse(agendamento);

    }
    
}
