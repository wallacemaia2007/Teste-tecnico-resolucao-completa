package br.teste.tecnico.AID.Teste.Tecnico.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.teste.tecnico.AID.Teste.Tecnico.model.entities.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID>{
    
}
