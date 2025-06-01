# 📝 ProjetoSpring_Grupo5_GestaoTarefas

Este é um sistema de gestão de tarefas desenvolvido em **Java com Spring Boot**, como parte de um projeto acadêmico.

---

## 📑 Sumário

- [⚙️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [📁 Estrutura do Projeto](#-estrutura-do-projeto)
- [📋 Funcionalidades](#-funcionalidades)
- [🚀 Como Executar o Projeto](#-como-executar-o-projeto)
- [📡 Endpoints da API](#-endpoints-da-api)
  - [🔢 Contagem por status (agrupado)](#-contagem-por-status-agrupado)
  - [🔢 Contagem de tarefas por status específico](#-contagem-de-tarefas-por-status-específico)
  - [🔍 Buscar tarefas por prioridade](#-buscar-tarefas-por-prioridade)
  - [🔍 Buscar tarefas por responsável](#-buscar-tarefas-por-responsável)
  - [📊 Listar tarefas ordenadas por prioridade](#-listar-tarefas-ordenadas-por-prioridade)
  - [📁 Operações com Listas de Tarefas](#-operações-com-listas-de-tarefas)
- [🧑‍💻 Desenvolvedores](#-desenvolvedores)
- [🗃️ Banco de Dados](#️-banco-de-dados)
- [📝 Licença](#-licença)

---

## ⚙️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 Database (ou outro banco relacional via JPA)
- Lombok

---

## 📁 Estrutura do Projeto

```
src/
└── main/
    └── java/
        └── com/
            └── gestao/
                └── gestaotarefas/
                    ├── controller/
                    ├── service/
                    ├── repository/
                    └── entity/
```

---

## 📋 Funcionalidades

- Cadastro e gerenciamento de tarefas.
- Associação de tarefas a listas.
- Filtros e consultas personalizadas:
  - Contagem de tarefas por status.
  - Consulta por prioridade.
  - Consulta por responsável.
  - Listagem ordenada por prioridade.

---

## 🚀 Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/lucaspaiolog/ProjetoSpring_Grupo5_GestaoTarefas.git
   ```

2. Abra o projeto em sua IDE (IntelliJ, Eclipse, VS Code).

3. Execute a classe principal:
   ```java
   com.gestao.gestaotarefas.GestaoTarefasApplication
   ```

4. Acesse a aplicação:
   ```
   http://localhost:8080
   ```

---

## 📡 Endpoints da API

### 🔢 Contagem por status (agrupado)
```
GET /tasks/count-by-status
```
**Resposta:**
```json
{
  "pendente": 3,
  "em andamento": 5,
  "concluída": 2
}
```

### 🔢 Contagem de tarefas por status específico
```
GET /tasks/count?status=pendente
```

### 🔍 Buscar tarefas por prioridade
```
GET /tasks/by-priority?priority=2
```

### 🔍 Buscar tarefas por responsável
```
GET /tasks/by-responsible?responsible=João
```

### 📊 Listar tarefas ordenadas por prioridade
```
GET /tasks/ordered-by-priority
```

### 📁 Operações com Listas de Tarefas

```
GET    /gestao-tarefas/lists/{id}/tasks
POST   /gestao-tarefas/lists?listId={listId}
DELETE /gestao-tarefas/lists/{id}
DELETE /gestao-tarefas/lists/{listId}/tasks/{taskId}
GET    /gestao-tarefas/lists/{listId}/tasks/{taskId}
GET    /gestao-tarefas/lists/{listId}/tasks/allTasks
GET    /gestao-tarefas/lists/{listId}/tasks/filterByStatus?status=pendente
GET    /gestao-tarefas/lists/{listId}/tasks/filterByResponsible?responsible=João
GET    /gestao-tarefas/lists/{listId}/tasks/filterByPriority?priority=2
```

---

## 🧑‍💻 Desenvolvedores

- Kevin Flay
- Lucas Paiolo
- Marcos Nascimento
- Gael Rodrigues

---

## 🗃️ Banco de Dados

O projeto está preparado para funcionar com qualquer banco relacional compatível com JPA. Um exemplo com banco **H2 em memória** pode ser configurado no `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

---

## 📝 Licença

Projeto acadêmico — uso livre para fins educacionais.