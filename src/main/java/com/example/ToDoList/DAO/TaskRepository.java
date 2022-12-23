package com.example.ToDoList.DAO;

import com.example.ToDoList.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {

    //neand komentarisao
    @Query(value = "select t from Task t inner join fetch t.priority p where p.id = :priorityId")
    List<Task>selectTasksByPriority(@Param("priorityId") Long priorityId);

 
}
