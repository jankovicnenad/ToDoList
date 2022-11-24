package com.example.ToDoList.service;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository task)
    {taskRepository = task;}

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskRepository.findById(id);
    }
    @Override
    public void save(Task task) {
        Priority priority = new Priority();
        Status status = new Status();
        task.setId(status.getId());
        task.setId(priority.getId());
        //task.setPriority(priority);
        taskRepository.save(task);
    }
    @Override
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

}