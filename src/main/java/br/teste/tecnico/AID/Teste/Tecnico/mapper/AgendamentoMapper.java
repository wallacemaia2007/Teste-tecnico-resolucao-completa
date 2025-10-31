package br.teste.tecnico.AID.Teste.Tecnico.mapper;
 
import org.springframework.stereotype.Component;

import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.AgendamentoRequest;
import br.teste.tecnico.AID.Teste.Tecnico.model.dtos.AgendamentoResponse;
import br.teste.tecnico.AID.Teste.Tecnico.model.entities.Agendamento;

@Component
public class AgendamentoMapper {

    public Agendamento toEntity(AgendamentoRequest request) {
        return new Agendamento(request.pacienteCpf(), request.medicoCrm(),
                request.especialidade(), request.inicio(), request.fim(), request.notas());
    }

    public AgendamentoResponse toResponse(Agendamento agendamento) {
        return new AgendamentoResponse(agendamento.getId(), agendamento.getPacienteCpf(),
                agendamento.getMedicoCrm(), agendamento.getEspecialidade(), agendamento.getInicio(),
                agendamento.getFim(), agendamento.getStatus(), agendamento.getDataCriacao(), agendamento.getNotas());
    }
}
