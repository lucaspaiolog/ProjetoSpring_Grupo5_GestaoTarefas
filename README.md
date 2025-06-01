# 📝 ProjetoSpring\_Grupo5\_GestaoTarefas

Este é um sistema de gestão de tarefas desenvolvido em **Java com Spring Boot**, como parte de um projeto acadêmico.

---

## ⚙️ Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* H2 Database (ou outro banco relacional via JPA)
* Lombok

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

* Cadastro e gerenciamento de tarefas.
* Associação de tarefas a listas.
* Filtros e consultas personalizadas:

  * Contagem de tarefas por status.
  * Consulta por prioridade.
  * Consulta por responsável.
  * Listagem ordenada por prioridade.

---

## ✨ Sumário

* [⚙️ Tecnologias Utilizadas](#⚙️-tecnologias-utilizadas)
* [📁 Estrutura do Projeto](#📁-estrutura-do-projeto)
* [📋 Funcionalidades](#📋-funcionalidades)
* [🚀 Como Executar o Projeto](#🚀-como-executar-o-projeto)
* [📡 Endpoints da API](#📡-endpoints-da-api)
* [🧑‍💻 Desenvolvedores](#🧑‍💻-desenvolvedores)
* [🗃️ Banco de Dados](#🗃️-banco-de-dados)
* [📝 Licença](#📝-licença)

---

## 🚀 Como Executar o Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/lucaspaiolog/ProjetoSpring_Grupo5_GestaoTarefas.git
   ```

2. Abra o projeto em sua IDE (IntelliJ, Eclipse, VS Code).

3. Execute a classe principal:

   ```
   com.gestao.gestaotarefas.GestaoTarefasApplication
   ```

4. Acesse a aplicação:

   ```
   http://localhost:8080
   ```

---

## 📡 Endpoints da API

### 🌎 Tarefas (`/gestao-tarefas`)

#### Listar todas as tarefas

```http
GET /gestao-tarefas
```

#### Buscar tarefa por ID

```http
GET /gestao-tarefas/{id}
```

#### Criar uma nova tarefa

```http
POST /gestao-tarefas
```

#### Atualizar uma tarefa

```http
PUT /gestao-tarefas/{id}
```

#### Deletar uma tarefa

```http
DELETE /gestao-tarefas/{id}
```

#### Atualizar status da tarefa

```http
PATCH /gestao-tarefas/{id}/status
```

**Body (text/plain):**

```
"novoStatus"
```

#### Atualizar responsável da tarefa

```http
PATCH /gestao-tarefas/{id}/responsible
```

**Body (text/plain):**

```
"novoResponsavel"
```

#### Contagem de tarefas agrupadas por status

```http
GET /gestao-tarefas/count-by-status
```

**Resposta:**

```json
{
  "pendente": 3,
  "em andamento": 5,
  "concluída": 2
}
```

#### Buscar tarefas por prioridade

```http
GET /gestao-tarefas/by-priority?priority=2
```

#### Buscar tarefas por responsável

```http
GET /gestao-tarefas/by-responsible?responsible=João
```

#### Listar tarefas ordenadas por prioridade

```http
GET /gestao-tarefas/ordered-by-priority
```

---

### 📂 Listas de Tarefas (`/gestao-tarefas/lists`)

#### Adicionar tarefa à lista

```http
POST /gestao-tarefas/lists?listId={listId}
```

**Body (application/json):**

```json
123
```

#### Remover tarefa da lista

```http
DELETE /gestao-tarefas/lists/{listId}/tasks/{taskId}
```

#### Buscar todas as tarefas de uma lista

```http
GET /gestao-tarefas/lists/{listId}/tasks/allTasks
```

#### Buscar tarefa específica dentro de uma lista

```http
GET /gestao-tarefas/lists/{listId}/tasks/{taskId}
```

#### Remover uma lista de tarefas

```http
DELETE /gestao-tarefas/lists/{id}
```

#### Buscar tarefas da lista por status

```http
GET /gestao-tarefas/lists/{listId}/tasks/filterByStatus?status=pendente
```

#### Buscar tarefas da lista por responsável

```http
GET /gestao-tarefas/lists/{listId}/tasks/filterByResponsible?responsible=João
```

#### Buscar tarefas da lista por prioridade

```http
GET /gestao-tarefas/lists/{listId}/tasks/filterByPriority?priority=2
```

---

## 🧑‍💻 Desenvolvedores

* Kevin Flay
* Lucas Paiolo
* Marcos Nascimento
* Gael Rodrigues

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
