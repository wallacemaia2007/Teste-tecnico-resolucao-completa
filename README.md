# Sistema de Agendamento de Consultas

Aplicação web desenvolvida em Java 17 + Spring Boot para gerenciamento de agendamentos de consultas médicas, com API RESTful para profissionais da saúde.

## 📋 Descrição

Este projeto é uma API REST para agendamento de consultas que permite criar agendamentos, validar datas e gerenciar informações de consultas médicas. A aplicação utiliza arquitetura em camadas (Controller, Service, Repository) e DTOs para separação de responsabilidades.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3+**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Maven**
- **Hibernate Validator**

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
│   │   ├── Agendamento.java
│   │   └── Status.java
│   └── dtos/
│       ├── AgendamentoRequest.java
│       ├── AgendamentoResponse.java
│       └── ErroResponse.java
└── exceptions/
    └── DataInvalidaException.java
```

## ⚙️ Funcionalidades Implementadas

### ✅ Criar Agendamento
- **Endpoint:** `POST /agendamentos`
- **Validações:** 
  - Campos obrigatórios
  - Data de fim deve ser maior que data de início
  - CPF válido (validação via Hibernate Validator)

### 📊 Modelo de Dados

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
- Maven 3.6+

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

4. **A aplicação estará disponível em:**
```
http://localhost:8080
```

## 📮 Exemplos de Requisições

### Criar um Agendamento

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

**Resposta de Sucesso (201 Created):**
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

**Resposta de Erro (400 Bad Request):**
```json
{
  "timestamp": "2025-10-31T10:30:00",
  "message": "A data de Fim é menor do que a data de início!",
  "details": "uri=/agendamentos"
}
```

## 🎯 Status do Desenvolvimento

### ✅ Implementado
- [x] Criar agendamento (POST)
- [x] Validação de datas (fim > início)
- [x] DTOs de Request e Response
- [x] Arquitetura em camadas
- [x] Tratamento global de exceções
- [x] Validação de CPF
- [x] Status padrão AGENDADO
- [x] Geração automática de dataCriacao

### ⏳ Pendente (Próximas Versões)
- [ ] Listar agendamentos com filtro por status (GET)
- [ ] Cancelar agendamento com motivo (PUT)
- [ ] Adicionar notas ao histórico (POST)
- [ ] Validação de horário já passado para cancelamento
- [ ] Documentação Swagger/OpenAPI
- [ ] Testes unitários e de integração
- [ ] Validação de conflitos de agenda
- [ ] Paginação nos endpoints de listagem
- [ ] Reagendamento de consultas
- [ ] Persistência em banco de dados relacional

## 🛠️ Melhorias Futuras

1. **Segurança:**
   - Implementar autenticação e autorização (Spring Security)
   - Validação de permissões por perfil (médico, recepcionista, admin)

2. **Validações de Negócio:**
   - Verificar conflitos de horário para mesmo médico
   - Validar CRM do médico
   - Implementar regras de agendamento (horário comercial, etc.)

3. **Performance:**
   - Cache de consultas frequentes
   - Índices no banco de dados
   - Paginação e ordenação

4. **Observabilidade:**
   - Logs estruturados
   - Métricas com Actuator
   - Health checks

5. **Testes:**
   - Testes unitários (JUnit 5 + Mockito)
   - Testes de integração
   - Cobertura mínima de 80%

## 📝 Notas Técnicas

- O projeto utiliza banco de dados em memória (H2), os dados são perdidos ao reiniciar a aplicação
- Status padrão de todo agendamento é `AGENDADO`
- A data de criação é gerada automaticamente no momento da criação
- O ID é gerado automaticamente como UUID

## 👨‍💻 Autor

Desenvolvido como teste técnico para vaga de Estágio em Desenvolvimento de Software.

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais e de avaliação técnica.
