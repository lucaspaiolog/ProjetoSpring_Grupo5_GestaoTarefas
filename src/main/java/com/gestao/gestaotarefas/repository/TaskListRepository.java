package com.gestao.gestaotarefas.repository;

import com.gestao.gestaotarefas.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {

}
