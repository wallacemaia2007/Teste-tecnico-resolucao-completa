package br.teste.tecnico.AID.Teste.Tecnico.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.teste.tecnico.AID.Teste.Tecnico.model.entities.Agendamento;
import br.teste.tecnico.AID.Teste.Tecnico.model.enums.Status;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID>{

    List<Agendamento> findAllByStatus(Status status);
    
}
