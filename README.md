Of course\! Here is an enhanced version of your `README.md` file.

It includes all the endpoints from your controllers, example requests using `curl`, clarifications on the database setup, and an improved structure to provide a better experience for developers.

You can copy and paste the following content directly into your `README.md` file.

````markdown
# 📝 ProjetoSpring_Grupo5_GestaoTarefas

A task management system developed in **Java with Spring Boot** as part of an academic project. This system allows users to create, manage, and organize tasks within lists through a RESTful API.

---

## 📑 Table of Contents

- [✨ Key Features](#-key-features)
- [⚙️ Technologies Used](#️-technologies-used)
- [🏁 Prerequisites](#-prerequisites)
- [🚀 How to Run the Project](#-how-to-run-the-project)
- [🗃️ Database Configuration](#️-database-configuration)
- [📁 Project Structure](#-project-structure)
- [📡 API Endpoints](#-api-endpoints)
  - [Tasks API](#tasks-api)
  - [Task Lists API](#task-lists-api)
- [📦 Data Models](#-data-models)
- [🧑‍💻 Developers](#-developers)
- [📝 License](#-license)

---

## ✨ Key Features

- CRUD operations for tasks (Create, Read, Update, Delete).
- Grouping tasks into lists.
- Advanced queries and filtering:
  - Count tasks aggregated by status.
  - Fetch tasks by priority or responsible person.
  - Sort tasks by priority.
  - Filter tasks within a specific list by status, responsible, or priority.

---

## ⚙️ Technologies Used

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring Web**: For building the RESTful API.
- **Spring Data JPA**: For data persistence.
- **PostgreSQL**: As the primary relational database.
- **H2 Database**: Available as an in-memory alternative for testing.
- **Lombok**: To reduce boilerplate code.
- **Maven**: For dependency management and project build.

---

## 🏁 Prerequisites

Before you begin, ensure you have the following installed:
- **JDK 17** or newer.
- **Apache Maven**.
- A **PostgreSQL** database instance, or Docker to run one.
- An IDE of your choice (e.g., IntelliJ IDEA, Eclipse, VS Code).
- An API client like [Postman](https://www.postman.com/) or `curl` for testing the endpoints.

---

## 🚀 How to Run the Project

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/lucaspaiolog/ProjetoSpring_Grupo5_GestaoTarefas.git](https://github.com/lucaspaiolog/ProjetoSpring_Grupo5_GestaoTarefas.git)
    cd ProjetoSpring_Grupo5_GestaoTarefas
    ```

2.  **Configure the database:**
    Open the `src/main/resources/application.yaml` file and update the database connection details (`url`, `username`, `password`) to match your local environment.

3.  **Build and Run the application:**
    You can run the app directly from your IDE by executing the `main` method in:
    ```java
    com.gestao.gestaotarefas.GestaoTarefasApplication
    ```
    Alternatively, you can use Maven:
    ```bash
    mvn spring-boot:run
    ```

4.  **Access the application:**
    The API will be available at `http://localhost:8080`.

---

## 🗃️ Database Configuration

The project is configured to use **PostgreSQL** by default.

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
      ddl-auto: update # Automatically updates the schema on startup
````

For a lightweight, zero-configuration setup (ideal for quick tests), you can switch to the **H2 in-memory database** by modifying your `application.yaml` (or `application.properties`).

-----

## 📁 Project Structure

The project follows a standard layered architecture for separation of concerns.

```
src/main/java/com/gestao/gestaotarefas/
├── controller/   # API endpoints (REST Controllers)
├── service/      # Business logic
├── repository/   # Data access layer (JPA Repositories)
└── entity/       # Data models (JPA Entities)
```

-----

## 📡 API Endpoints

The base path for all endpoints is `/gestao-tarefas`.

### Tasks API

Endpoints for managing individual tasks.

\<details\>
\<summary\>\<strong\>POST /tasks - Create a new task\</strong\>\</summary\>

Creates a new task. The `taskList` can be null.

  - **Request Body:**
    ```json
    {
      "description": "Implement the authentication feature",
      "priority": 1,
      "status": "Pendente",
      "responsible": "Gael Rodrigues",
      "taskList": null
    }
    ```
  - **Example `curl`:**
    ```bash
    curl -X POST http://localhost:8080/gestao-tarefas \
    -H "Content-Type: application/json" \
    -d '{"description": "New API Task", "priority": 1, "status": "Pendente", "responsible": "Dev"}'
    ```

\</details\>

\<details\>
\<summary\>\<strong\>GET /tasks - Get all tasks\</strong\>\</summary\>

Retrieves a list of all tasks in the system.

  - **Example `curl`:**
    ```bash
    curl http://localhost:8080/gestao-tarefas
    ```

\</details\>

\<details\>
\<summary\>\<strong\>GET /tasks/{id} - Get task by ID\</strong\>\</summary\>

Retrieves a single task by its unique ID.

  - **Example `curl`:**
    ```bash
    curl http://localhost:8080/gestao-tarefas/1
    ```

\</details\>

\<details\>
\<summary\>\<strong\>PUT /tasks/{id} - Update a task\</strong\>\</summary\>

Updates all fields of an existing task.

  - **Example `curl`:**
    ```bash
    curl -X PUT http://localhost:8080/gestao-tarefas/1 \
    -H "Content-Type: application/json" \
    -d '{"description": "Updated Task Description", "priority": 2, "status": "Em Andamento", "responsible": "Marcos"}'
    ```

\</details\>

\<details\>
\<summary\>\<strong\>PATCH /tasks/{id}/status - Update task status\</strong\>\</summary\>

Partially updates the status of a specific task.

  - **Example `curl`:**
    ```bash
    curl -X PATCH http://localhost:8080/gestao-tarefas/1/status \
    -H "Content-Type: text/plain" \
    -d "Concluída"
    ```

\</details\>

\<details\>
\<summary\>\<strong\>PATCH /tasks/{id}/responsible - Update task responsible\</strong\>\</summary\>

Partially updates the responsible person for a task.

  - **Example `curl`:**
    ```bash
    curl -X PATCH http://localhost:8080/gestao-tarefas/1/responsible \
    -H "Content-Type: text/plain" \
    -d "Kevin Flay"
    ```

\</details\>

\<details\>
\<summary\>\<strong\>DELETE /tasks/{id} - Delete a task\</strong\>\</summary\>

Deletes a task by its ID.

  - **Example `curl`:**
    ```bash
    curl -X DELETE http://localhost:8080/gestao-tarefas/1
    ```

\</details\>

\<details\>
\<summary\>\<strong\>GET /tasks/count-by-status - Count tasks by status\</strong\>\</summary\>

Returns a count of tasks, grouped by their status.

  - **Example `curl`:**
    ```bash
    curl http://localhost:8080/gestao-tarefas/count-by-status
    ```
  - **Example Response:**
    ```json
    {
      "Pendente": 5,
      "Em Andamento": 3,
      "Concluída": 10
    }
    ```

\</details\>

\<details\>
\<summary\>\<strong\>GET /tasks/by-priority - Find tasks by priority\</strong\>\</summary\>

Finds all tasks that match a given priority level.

  - **Example `curl`:**
    ```bash
    curl "http://localhost:8080/gestao-tarefas/by-priority?priority=1"
    ```

\</details\>

\<details\>
\<summary\>\<strong\>GET /tasks/by-responsible - Find tasks by responsible\</strong\>\</summary\>

Finds all tasks assigned to a specific person.

  - **Example `curl`:**
    ```bash
    curl "http://localhost:8080/gestao-tarefas/by-responsible?responsible=Lucas%20Paiolo"
    ```

\</details\>

\<details\>
\<summary\>\<strong\>GET /tasks/ordered-by-priority - List tasks by priority\</strong\>\</summary\>

Returns all tasks sorted in ascending order of priority.

  - **Example `curl`:**
    ```bash
    curl http://localhost:8080/gestao-tarefas/ordered-by-priority
    ```

\</details\>

### Task Lists API

Endpoints for managing lists and their associated tasks.
*Note: To create a `TaskList`, you must first save it to the database. The current API does not expose a direct endpoint for `TaskList` creation; it must be done at the database level or via cascading from another entity.*

\<details\>
\<summary\>\<strong\>POST /lists - Add a task to a list\</strong\>\</summary\>

Associates an existing task with an existing task list.

  - **Query Parameters:** `listId`, `taskId`
  - **Example `curl`:**
    ```bash
    curl -X POST "http://localhost:8080/gestao-tarefas/lists?listId=1&taskId=5"
    ```

\</details\>

\<details\>
\<summary\>\<strong\>GET /lists/{id}/tasks - Get all tasks from a list\</strong\>\</summary\>

Retrieves all tasks belonging to a specific list.

  - **Example `curl`:**
    ```bash
    curl http://localhost:8080/gestao-tarefas/lists/1/tasks
    ```

\</details\>

\<details\>
\<summary\>\<strong\>DELETE /lists/{id} - Delete a task list\</strong\>\</summary\>

Deletes an entire task list and, due to cascading, all tasks within it.

  - **Example `curl`:**
    ```bash
    curl -X DELETE http://localhost:8080/gestao-tarefas/lists/1
    ```

\</details\>

\<details\>
\<summary\>\<strong\>DELETE /lists/{listId}/tasks/{taskId} - Remove task from list\</strong\>\</summary\>

Removes the association between a task and a list. The task itself is not deleted.

  - **Example `curl`:**
    ```bash
    curl -X DELETE http://localhost:8080/gestao-tarefas/lists/1/tasks/5
    ```

\</details\>

\<details\>
\<summary\>\<strong\>GET /lists/{listId}/tasks/filterByStatus - Filter list by status\</strong\>\</summary\>

Gets all tasks in a list filtered by a specific status.

  - **Example `curl`:**
    ```bash
    curl "http://localhost:8080/gestao-tarefas/lists/1/tasks/filterByStatus?status=Pendente"
    ```

\</details\>

\<details\>
\<summary\>\<strong\>GET /lists/{listId}/tasks/filterByPriority - Filter list by priority\</strong\>\</summary\>

Gets all tasks in a list filtered by a specific priority level.

  - **Example `curl`:**
    ```bash
    curl "http://localhost:8080/gestao-tarefas/lists/1/tasks/filterByPriority?priority=1"
    ```

\</details\>

\<details\>
\<summary\>\<strong\>GET /lists/{listId}/tasks/filterByResponsible - Filter list by responsible\</strong\>\</summary\>

Gets all tasks in a list filtered by a specific responsible person.

  - **Example `curl`:**
    ```bash
    curl "http://localhost:8080/gestao-tarefas/lists/1/tasks/filterByResponsible?responsible=Gael%20Rodrigues"
    ```

\</details\>

-----

## 📦 Data Models

### Task

Represents a single task item.

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

### TaskList

Represents a list that contains multiple tasks.

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

## 🧑‍💻 Developers

  - Kevin Flay
  - Lucas Paiolo
  - Marcos Nascimento
  - Gael Rodrigues

-----

## 📝 License

This is an academic project and is free for educational use.

```
```
