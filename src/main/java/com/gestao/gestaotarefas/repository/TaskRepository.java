package com.gestao.gestaotarefas.repository;

import com.gestao.gestaotarefas.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {






    // Nativa
    @Query(value = "SELECT status, COUNT(*) FROM task GROUP BY status", nativeQuery = true)
    List<Object[]> countTasksByStatus();

    // Derivadas
    List<Task> findByPriority(int priority);

    List<Task> findByResponsible(String responsible);

    // JPQL
    @Query("SELECT t FROM Task t ORDER BY t.priority ASC")
    List<Task> findAllOrderByPriority();






}
