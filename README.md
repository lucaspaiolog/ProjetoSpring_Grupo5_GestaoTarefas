# 📝 ProjetoSpring_Grupo5_GestaoTarefas

Um sistema de gestão de tarefas desenvolvido em **Java com Spring Boot** como parte de um projeto acadêmico. Este sistema permite que os usuários criem, gerenciem e organizem tarefas em listas através de uma API RESTful.

---

## 📑 Sumário

- [✨ Principais Funcionalidades](#-principais-funcionalidades)
- [⚙️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [🏁 Pré-requisitos](#-pré-requisitos)
- [🚀 Como Executar o Projeto](#-como-executar-o-projeto)
- [🗃️ Configuração do Banco de Dados](#️-configuração-do-banco-de-dados)
- [📁 Estrutura do Projeto](#-estrutura-do-projeto)
- [📡 Endpoints da API](#-endpoints-da-api)
  - [API de Tarefas](#api-de-tarefas)
  - [API de Listas de Tarefas](#api-de-listas-de-tarefas)
- [📦 Modelos de Dados](#-modelos-de-dados)
- [🧑‍💻 Desenvolvedores](#-desenvolvedores)
- [📝 Licença](#-licença)

---

## ✨ Principais Funcionalidades

- Operações CRUD para tarefas (Criar, Ler, Atualizar, Deletar).
- Agrupamento de tarefas em listas.
- Consultas e filtros avançados:
  - Contagem de tarefas agregadas por status.
  - Busca de tarefas por prioridade ou responsável.
  - Ordenação de tarefas por prioridade.
  - Filtragem de tarefas dentro de uma lista específica por status, responsável ou prioridade.

---

## ⚙️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring Web**: Para a construção da API RESTful.
- **Spring Data JPA**: Para a persistência de dados.
- **PostgreSQL**: Como o banco de dados relacional principal.
- **H2 Database**: Disponível como uma alternativa em memória para testes.
- **Lombok**: Para reduzir código boilerplate.
- **Maven**: Para gerenciamento de dependências e build do projeto.

---

## 🏁 Pré-requisitos

Antes de começar, garanta que você tenha o seguinte instalado:
- **JDK 17** ou superior.
- **Apache Maven**.
- Uma instância de banco de dados **PostgreSQL**, ou Docker para executá-la.
- Uma IDE de sua escolha (ex: IntelliJ IDEA, Eclipse, VS Code).
- Um cliente de API como [Postman](https://www.postman.com/) ou `curl` para testar os endpoints.

---

## 🚀 Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/lucaspaiolog/ProjetoSpring_Grupo5_GestaoTarefas.git](https://github.com/lucaspaiolog/ProjetoSpring_Grupo5_GestaoTarefas.git)
    cd ProjetoSpring_Grupo5_GestaoTarefas
    ```

2.  **Configure o banco de dados:**
    Abra o arquivo `src/main/resources/application.yaml` e atualize os detalhes de conexão do banco de dados (`url`, `username`, `password`) para corresponder ao seu ambiente local.

3.  **Compile e execute a aplicação:**
    Você pode executar a aplicação diretamente da sua IDE, executando o método `main` em:
    ```java
    com.gestao.gestaotarefas.GestaoTarefasApplication
    ```
    Alternativamente, você pode usar o Maven:
    ```bash
    mvn spring-boot:run
    ```

4.  **Acesse a aplicação:**
    A API estará disponível em `http://localhost:8080`.

---

## 🗃️ Configuração do Banco de Dados

O projeto está configurado para usar **PostgreSQL** por padrão.

**`src/main/resources/application.yaml`:**
```yaml
spring:
  application:
    name: gestaoTarefas

  datasource:
    url: jdbc:postgresql://localhost:5432/tarefas
    username: postgres
    password: your_password_here
    driver-class-name: org.postgresql.Driver

  jpa:
    database-platform: org.hibernate.dialect.PostgreSQLDialect
    show-sql: true
    hibernate:
      ddl-auto: update # Atualiza o schema automaticamente na inicialização
````

Para uma configuração leve e sem necessidade de instalação (ideal para testes rápidos), você pode mudar para o banco de dados em memória **H2** modificando seu `application.yaml` (ou `application.properties`).

-----

## 📁 Estrutura do Projeto

O projeto segue uma arquitetura em camadas padrão para a separação de responsabilidades.

```
src/main/java/com/gestao/gestaotarefas/
├── controller/   # Endpoints da API (REST Controllers)
├── service/      # Lógica de negócio
├── repository/   # Camada de acesso a dados (JPA Repositories)
└── entity/       # Modelos de dados (JPA Entities)
```

-----

## 📡 Endpoints da API

O caminho base para todos os endpoints é `/gestao-tarefas`.

### API de Tarefas

Endpoints para o gerenciamento de tarefas individuais.

POST /tasks - Criar uma nova tarefa

Cria uma nova tarefa. O campo `taskList` pode ser nulo.

  - **Corpo da Requisição:**
    ```json
    {
      "description": "Implementar a funcionalidade de autenticação",
      "priority": 1,
      "status": "Pendente",
      "responsible": "Gael Rodrigues",
      "taskList": null
    }
    ```
  - **Exemplo `curl`:**
    ```bash
    curl -X POST http://localhost:8080/gestao-tarefas \
    -H "Content-Type: application/json" \
    -d '{"description": "Nova Tarefa via API", "priority": 1, "status": "Pendente", "responsible": "Dev"}'
    ```

GET /tasks - Obter todas as tarefas

Recupera uma lista de todas as tarefas no sistema.

  - **Exemplo `curl`:**
    ```bash
    curl http://localhost:8080/gestao-tarefas
    ```
GET /tasks/{id} - Obter tarefa por ID

Recupera uma única tarefa pelo seu ID único.

  - **Exemplo `curl`:**
    ```bash
    curl http://localhost:8080/gestao-tarefas/1
    ```
PUT /tasks/{id} - Atualizar uma tarefa

Atualiza todos os campos de uma tarefa existente.

  - **Exemplo `curl`:**
    ```bash
    curl -X PUT http://localhost:8080/gestao-tarefas/1 \
    -H "Content-Type: application/json" \
    -d '{"description": "Descrição da Tarefa Atualizada", "priority": 2, "status": "Em Andamento", "responsible": "Marcos"}'
    ```
PATCH /tasks/{id}/status - Atualizar status da tarefa

Atualiza parcialmente o status de uma tarefa específica.

  - **Exemplo `curl`:**
    ```bash
    curl -X PATCH http://localhost:8080/gestao-tarefas/1/status \
    -H "Content-Type: text/plain" \
    -d "Concluída"
    ```
PATCH /tasks/{id}/responsible - Atualizar responsável da tarefa

Atualiza parcialmente a pessoa responsável por uma tarefa.

  - **Exemplo `curl`:**
    ```bash
    curl -X PATCH http://localhost:8080/gestao-tarefas/1/responsible \
    -H "Content-Type: text/plain" \
    -d "Kevin Flay"
    ```
DELETE /tasks/{id} - Deletar uma tarefa

Deleta uma tarefa pelo seu ID.

  - **Exemplo `curl`:**
    ```bash
    curl -X DELETE http://localhost:8080/gestao-tarefas/1
    ```
/tasks/count-by-status - Contar tarefas por status

Retorna a contagem de tarefas, agrupadas por status.

  - **Exemplo `curl`:**
    ```bash
    curl http://localhost:8080/gestao-tarefas/count-by-status
    ```
  - **Exemplo de Resposta:**
    ```json
    {
      "Pendente": 5,
      "Em Andamento": 3,
      "Concluída": 10
    }
    ```
GET /tasks/by-priority - Buscar tarefas por prioridade

Encontra todas as tarefas que correspondem a um determinado nível de prioridade.

  - **Exemplo `curl`:**
    ```bash
    curl "http://localhost:8080/gestao-tarefas/by-priority?priority=1"
    ```
GET /tasks/by-responsible - Buscar tarefas por responsável

Encontra todas as tarefas atribuídas a uma pessoa específica.

  - **Exemplo `curl`:**
    ```bash
    curl "http://localhost:8080/gestao-tarefas/by-responsible?responsible=Lucas%20Paiolo"
    ```
GET /tasks/ordered-by-priority - Listar tarefas por prioridade

Retorna todas as tarefas ordenadas por prioridade em ordem ascendente.

  - **Exemplo `curl`:**
    ```bash
    curl http://localhost:8080/gestao-tarefas/ordered-by-priority
    ```


### API de Listas de Tarefas

Endpoints para gerenciar listas e suas tarefas associadas.
*Nota: Para criar uma `TaskList`, você deve primeiro salvá-la no banco de dados. A API atual não expõe um endpoint direto para a criação de `TaskList`; isso deve ser feito no nível do banco de dados ou via cascata de outra entidade.*

POST /lists - Adicionar uma tarefa a uma lista

Associa uma tarefa existente a uma lista de tarefas existente.

  - **Parâmetros da Query:** `listId`, `taskId`
  - **Exemplo `curl`:**
    ```bash
    curl -X POST "http://localhost:8080/gestao-tarefas/lists?listId=1&taskId=5"
    ```
GET /lists/{id}/tasks - Obter todas as tarefas de uma lista

Recupera todas as tarefas que pertencem a uma lista específica.

  - **Exemplo `curl`:**
    ```bash
    curl http://localhost:8080/gestao-tarefas/lists/1/tasks
    ```
DELETE /lists/{id} - Deletar uma lista de tarefas

Deleta uma lista de tarefas inteira e, devido ao cascateamento, todas as tarefas dentro dela.

  - **Exemplo `curl`:**
    ```bash
    curl -X DELETE http://localhost:8080/gestao-tarefas/lists/1
    ```
DELETE /lists/{listId}/tasks/{taskId} - Remover tarefa da lista

Remove a associação entre uma tarefa e uma lista. A tarefa em si não é deletada.

  - **Exemplo `curl`:**
    ```bash
    curl -X DELETE http://localhost:8080/gestao-tarefas/lists/1/tasks/5
    ```
GET /lists/{listId}/tasks/filterByStatus - Filtrar lista por status

Obtém todas as tarefas em uma lista filtradas por um status específico.

  - **Exemplo `curl`:**
    ```bash
    curl "http://localhost:8080/gestao-tarefas/lists/1/tasks/filterByStatus?status=Pendente"
    ```
GET /lists/{listId}/tasks/filterByPriority - Filtrar lista por prioridade

Obtém todas as tarefas em uma lista filtradas por um nível de prioridade específico.

  - **Exemplo `curl`:**
    ```bash
    curl "http://localhost:8080/gestao-tarefas/lists/1/tasks/filterByPriority?priority=1"
    ```
GET /lists/{listId}/tasks/filterByResponsible - Filtrar lista por responsável

Obtém todas as tarefas em uma lista filtradas por uma pessoa responsável específica.

  - **Exemplo `curl`:**
    ```bash
    curl "http://localhost:8080/gestao-tarefas/lists/1/tasks/filterByResponsible?responsible=Gael%20Rodrigues"
    ```

-----

## 📦 Modelos de Dados

### Tarefa (Task)

Representa um item de tarefa individual.

```java
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int priority;
    private String status;
    private String responsible;

    @ManyToOne
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;
}
```

### Lista de Tarefas (TaskList)

Representa uma lista que contém múltiplas tarefas.

```java
@Entity
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
```

-----

## 🧑‍💻 Desenvolvedores

  - Kevin Flay
  - Lucas Paiolo
  - Marcos Nascimento
  - Gael Rodrigues

-----

## 📝 Licença

Este é um projeto acadêmico e é de uso livre para fins educacionais.
