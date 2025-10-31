package br.teste.tecnico.AID.Teste.Tecnico.model.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_agendamento")
public class Agendamento {

    @Id
    @GeneratedValue
    private UUID id;

    @CPF
    private String pacienteCpf;
    private String medicoCrm;
    private String especialidade;

    private LocalDateTime inicio;
    private LocalDateTime fim;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime dataCriacao;

    @ElementCollection
    private List<String> notas;

    public Agendamento() {
    }

    public Agendamento(String pacienteCpf, String medicoCrm, String especialidade, LocalDateTime inicio,
            LocalDateTime fim, List<String> notas) {
        this.pacienteCpf = pacienteCpf;
        this.medicoCrm = medicoCrm;
        this.especialidade = especialidade;
        this.inicio = inicio;
        this.fim = fim;
        this.status = Status.AGENDADO;
        this.dataCriacao = LocalDateTime.now();
        this.notas = notas;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPacienteCpf() {
        return pacienteCpf;
    }

    public void setPacienteCpf(String pacienteCpf) {
        this.pacienteCpf = pacienteCpf;
    }

    public String getMedicoCrm() {
        return medicoCrm;
    }

    public void setMedicoCrm(String medicoCrm) {
        this.medicoCrm = medicoCrm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<String> getNotas() {
        return notas;
    }

    public void setNotas(List<String> notas) {
        this.notas = notas;
    }

}
