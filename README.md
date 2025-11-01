# Sistema de Agendamento de Consultas

Aplicação web desenvolvida em Java 17 + Spring Boot para gerenciamento de agendamentos de consultas médicas, com API RESTful completa para profissionais da saúde.

## 📋 Sobre o Projeto

Esta é uma **solução completa** do teste técnico proposto no repositório [Teste-tecnico-Agendamento-de-consultas](https://github.com/wallacemaia2007/Teste-tecnico-Agendamento-de-consultas). Todos os requisitos foram implementados seguindo as melhores práticas de desenvolvimento com Spring Boot.

O projeto é uma API REST para agendamento de consultas que permite criar agendamentos, listar consultas por status, cancelar agendamentos, adicionar notas e gerenciar informações de consultas médicas. A aplicação utiliza arquitetura em camadas (Controller, Service, Repository) e DTOs para separação de responsabilidades.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Maven**
- **Hibernate Validator**
- **SpringDoc OpenAPI 2.3.0** (Swagger UI)

## 📁 Estrutura do Projeto

```
br.teste.tecnico.AID.Teste.Tecnico
├── controllers/
│   ├── AgendamentoController.java
│   └── GlobalExceptionHandler.java
├── services/
│   └── AgendamentoService.java
├── repository/
│   └── AgendamentoRepository.java
├── mapper/
│   └── AgendamentoMapper.java
├── model/
│   ├── entities/
│   │   └── Agendamento.java
│   ├── enums/
│   │   └── Status.java
│   └── dtos/
│       ├── AgendamentoRequest.java
│       ├── AgendamentoResponse.java
│       ├── CancelamentoRequest.java
│       ├── NotaRequest.java
│       └── ErroResponse.java
└── exceptions/
    ├── DataInvalidaException.java
    ├── IdNaoExcistenteException.java
    ├── IdNuloException.java
    ├── MotivoVazioException.java
    ├── AgendamentoJaCanceladoException.java
    └── AgendamentoJaPassouException.java
```

## ⚙️ Funcionalidades Implementadas

### ✅ 1. Criar Agendamento
- **Endpoint:** `POST /agendamentos`
- **Validações:** 
  - Campos obrigatórios
  - Data de fim deve ser maior que data de início
  - CPF válido (validação via Hibernate Validator)
  - Status padrão: AGENDADO
  - Data de criação gerada automaticamente

### ✅ 2. Listar Agendamentos por Status
- **Endpoint:** `GET /agendamentos/{status}`
- **Parâmetros:** status (AGENDADO ou CANCELADO)
- **Retorna:** Lista de agendamentos filtrados pelo status

### ✅ 3. Listar Todos os Agendamentos
- **Endpoint:** `GET /agendamentos`
- **Retorna:** Lista completa de todos os agendamentos

### ✅ 4. Adicionar Nota ao Agendamento
- **Endpoint:** `POST /agendamentos/{id}/notas`
- **Função:** Adiciona uma nota ao histórico do agendamento
- **Validações:**
  - ID deve existir
  - ID não pode ser nulo

### ✅ 5. Cancelar Agendamento
- **Endpoint:** `PUT /agendamentos/{id}/cancelar`
- **Função:** Cancela um agendamento com motivo obrigatório
- **Validações:**
  - ID deve existir
  - Agendamento não pode já estar cancelado
  - Horário não pode ter passado
  - Motivo do cancelamento é obrigatório

## 📊 Modelo de Dados

**Entidade Agendamento:**
- `id` (UUID, gerado automaticamente)
- `pacienteCpf` (String, obrigatório, validado)
- `medicoCrm` (String, obrigatório)
- `especialidade` (String, obrigatório)
- `inicio` (LocalDateTime, obrigatório)
- `fim` (LocalDateTime, obrigatório)
- `status` (Enum: AGENDADO, CANCELADO)
- `dataCriacao` (LocalDateTime, gerado automaticamente)
- `notas` (Lista de Strings, opcional)

## 🔧 Como Executar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven 3.9+

### Passos para execução

1. **Clone o repositório:**
```bash
git clone <url-do-repositorio>
cd AID-Teste-Tecnico
```

2. **Compile o projeto:**
```bash
mvn clean install
```

3. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

4. **Acesse a aplicação:**
```
API: http://localhost:8080
Swagger UI: http://localhost:8080/swagger-ui.html
H2 Console: http://localhost:8080/h2-console
```

## 📮 Exemplos de Requisições

### 1. Criar um Agendamento

**Endpoint:** `POST /agendamentos`

**Body (JSON):**
```json
{
  "pacienteCpf": "123.456.789-09",
  "medicoCrm": "CRM123456",
  "especialidade": "Cardiologia",
  "inicio": "2025-11-01T14:00:00",
  "fim": "2025-11-01T15:00:00",
  "notas": ["Paciente pontual", "Trazer exames anteriores"]
}
```

**Resposta (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "pacienteCpf": "123.456.789-09",
  "medicoCrm": "CRM123456",
  "especialidade": "Cardiologia",
  "inicio": "2025-11-01T14:00:00",
  "fim": "2025-11-01T15:00:00",
  "status": "AGENDADO",
  "dataCriacao": "2025-10-31T10:30:00",
  "notas": ["Paciente pontual", "Trazer exames anteriores"]
}
```

### 2. Listar Agendamentos por Status

**Endpoint:** `GET /agendamentos/AGENDADO`

**Resposta (200 OK):**
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "pacienteCpf": "123.456.789-09",
    "medicoCrm": "CRM123456",
    "especialidade": "Cardiologia",
    "inicio": "2025-11-01T14:00:00",
    "fim": "2025-11-01T15:00:00",
    "status": "AGENDADO",
    "dataCriacao": "2025-10-31T10:30:00",
    "notas": []
  }
]
```

### 3. Adicionar Nota ao Agendamento

**Endpoint:** `POST /agendamentos/{id}/notas`

**Body (JSON):**
```json
{
  "descricao": "Paciente chegou mais cedo"
}
```

**Resposta (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "pacienteCpf": "123.456.789-09",
  "medicoCrm": "CRM123456",
  "especialidade": "Cardiologia",
  "inicio": "2025-11-01T14:00:00",
  "fim": "2025-11-01T15:00:00",
  "status": "AGENDADO",
  "dataCriacao": "2025-10-31T10:30:00",
  "notas": ["Paciente chegou mais cedo"]
}
```

### 4. Cancelar Agendamento

**Endpoint:** `PUT /agendamentos/{id}/cancelar`

**Body (JSON):**
```json
{
  "motivo": "Paciente solicitou remarcação"
}
```

**Resposta (200 OK):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "pacienteCpf": "123.456.789-09",
  "medicoCrm": "CRM123456",
  "especialidade": "Cardiologia",
  "inicio": "2025-11-01T14:00:00",
  "fim": "2025-11-01T15:00:00",
  "status": "CANCELADO",
  "dataCriacao": "2025-10-31T10:30:00",
  "notas": []
}
```

### Exemplos de Respostas de Erro

**Data inválida (400 Bad Request):**
```json
{
  "timestamp": "2025-10-31T10:30:00",
  "message": "A data de Fim é menor do que a data de início!",
  "details": "uri=/agendamentos"
}
```

**ID não encontrado (404 Not Found):**
```json
{
  "timestamp": "2025-10-31T10:30:00",
  "message": "O id mencionado não existe!",
  "details": "uri=/agendamentos/123e4567-e89b-12d3-a456-426614174000/cancelar"
}
```

**Agendamento já cancelado (400 Bad Request):**
```json
{
  "timestamp": "2025-10-31T10:30:00",
  "message": "O Agendamento mencionado já foi cancelado!",
  "details": "uri=/agendamentos/550e8400-e29b-41d4-a716-446655440000/cancelar"
}
```

## 🎯 Requisitos do Teste Técnico - Status

### ✅ Funcionalidades Obrigatórias (100% Completo)
- [x] Criar agendamento (POST)
- [x] Listar agendamentos com filtro por status (GET)
- [x] Cancelar agendamento com motivo (PUT)
- [x] Adicionar notas ao histórico (POST)

### ✅ Validações Implementadas
- [x] Validação de datas (fim > início)
- [x] Validação de CPF
- [x] Validação de horário já passado para cancelamento
- [x] Validação de agendamento já cancelado
- [x] Validação de motivo obrigatório no cancelamento
- [x] Validação de ID existente
- [x] Status padrão AGENDADO
- [x] Geração automática de dataCriacao

### ✅ Arquitetura e Boas Práticas
- [x] Arquitetura em camadas (Controller, Service, Repository)
- [x] DTOs de Request e Response
- [x] Tratamento global de exceções
- [x] Validação com Hibernate Validator
- [x] Uso de Enums para Status
- [x] Mapper para conversão entre Entity e DTO
- [x] Documentação com Swagger/OpenAPI

## 📚 Documentação da API

A documentação completa da API está disponível via Swagger UI após executar a aplicação:

**URL:** `http://localhost:8080/swagger-ui.html`

Através do Swagger você pode:
- Visualizar todos os endpoints disponíveis
- Testar as requisições diretamente no navegador
- Ver os modelos de dados (schemas)
- Consultar exemplos de requisições e respostas

## 🗄️ Banco de Dados

### H2 Console

O projeto utiliza banco de dados em memória H2. Você pode acessar o console em:

**URL:** `http://localhost:8080/h2-console`

**Configurações de conexão:**
- JDBC URL: `jdbc:h2:mem:testeTecnico`
- Username: `sa`
- Password: *(deixar em branco)*

**Nota:** Os dados são perdidos ao reiniciar a aplicação.

## 🛡️ Tratamento de Exceções

O sistema possui tratamento global de exceções com respostas padronizadas:

| Exceção | Status HTTP | Descrição |
|---------|-------------|-----------|
| `DataInvalidaException` | 400 | Data de fim anterior à data de início |
| `IdNaoExcistenteException` | 404 | ID não encontrado no banco |
| `IdNuloException` | 400 | ID fornecido é nulo |
| `MotivoVazioException` | 409 | Motivo de cancelamento vazio |
| `AgendamentoJaCanceladoException` | 400 | Tentativa de cancelar agendamento já cancelado |
| `AgendamentoJaPassouException` | 400 | Tentativa de cancelar agendamento com horário passado |

## 📝 Notas Técnicas

- O projeto utiliza banco de dados em memória (H2), os dados são perdidos ao reiniciar a aplicação
- Status padrão de todo agendamento é `AGENDADO`
- A data de criação é gerada automaticamente no momento da criação
- O ID é gerado automaticamente como UUID
- CPF deve estar no formato válido para passar na validação
- Notas são opcionais e podem ser adicionadas a qualquer momento
- Motivo é obrigatório para cancelamento

## 🎓 Referência ao Teste Técnico Original

Este projeto é uma **implementação completa** do desafio proposto em:

**Repositório Original:** [Teste-tecnico-Agendamento-de-consultas](https://github.com/wallacemaia2007/Teste-tecnico-Agendamento-de-consultas)

### Requisitos Atendidos

✅ Todos os requisitos funcionais foram implementados  
✅ Arquitetura em camadas conforme especificado  
✅ Validações de negócio implementadas  
✅ Tratamento de exceções robusto  
✅ Documentação completa com Swagger  
✅ Código organizado e seguindo boas práticas  
