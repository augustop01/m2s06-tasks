# REST API - Mini Projeto de Gerenciamento de Tarefas

A API simulada oferece operações básicas utilizando os métodos HTTP: GET, POST, PUT e DELETE, para manipulação de tarefas. O corpo da requisição utilizado nos métodos POST e PUT deve ser um objeto JSON contendo os detalhes da tarefa.

Dado o objetivo do exercício, a aplicação simula uma API sem estar realmente conectada a um Banco de Dados, contendo então uma Database simulada através da classe "Database".

## Recursos Disponíveis

### Tarefa

Indica uma tarefa a ser gerenciada.

- `id` (Integer [gerado automaticamente]): Identificador único da tarefa.
- `description` (String): Descrição da tarefa.
- `status` ([ENUM]): Status da tarefa, podendo ser "DOING", "PENDING", "COMPLETE").
- `priority` ([ENUM]): Prioridade da tarefa, podendo ser "LOW", "MEDIUM", "HIGH".
- `inCharge` (String): Nome da pessoa responsável pela tarefa.

## Endpoints

### GET (todas as Tarefas)

```
localhost:8080/clamed/task
```

### GET (tarefa por status)

```
localhost:8080/clamed/task?status=DOING
localhost:8080/clamed/task?status=PENDING
localhost:8080/clamed/task?status=COMPLETED
```

### GET (tarefa por prioridade)

```
localhost:8080/clamed/task?priority=LOW
localhost:8080/clamed/task?priority=MEDIUM
localhost:8080/clamed/task?priority=HIGH
```

### GET (tarefa por responsável)

```
localhost:8080/clamed/task?inCharge=NomeDoResponsavel
```

#### Exemplo de Response

```json
[
  {
    "id": 1,
    "description": "Tarefa 1",
    "status": "DOING",
    "priority": "MEDIUM",
    "inCharge": "Um Nome"
  },
  {
    "id": 2,
    "description": "Tarefa 2",
    "status": "COMPLETED",
    "priority": "HIGH",
    "inCharge": "Outro Nome"
  }
]
```

### POST (nova tarefa)

```
localhost:8080/clamed/task
```

#### Request Body (exemplo)

```json
{
  "description": "Tarefa X",
  "status": "DOING",
  "priority": "HIGH",
  "inCharge": "Nome do Responsável"
}
```

#### Exemplo de Response

```json
{
  "id": 1,
  "description": "Tarefa X",
  "status": "DOING",
  "priority": "HIGH",
  "inCharge": "Nome do Responsável"
}
```

### PUT (atualizar tarefa existente)

```
localhost:8080/clamed/task/{id}
```

#### Request Body (exemplo)

```json
{
  "description": "Tarefa Atualizada",
  "status": "DONE",
  "priority": "MEDIUM",
  "inCharge": "Nome do Responsável"
}
```

#### Exemplo de Response

```json
{
  "id": 1,
  "description": "Tarefa Atualizada",
  "status": "DONE",
  "priority": "MEDIUM",
  "inCharge": "Nome do Responsável"
}
```

### DELETE (excluir tarefa)

```
localhost:8080/clamed/task/{id}
```

#### Exemplo de Response (exibirá a tarefa recém excluída)

```json
{
  "id": 1,
  "description": "Tarefa X",
  "status": "DONE",
  "priority": "MEDIUM",
  "inCharge": "Nome do Responsável"
}
```