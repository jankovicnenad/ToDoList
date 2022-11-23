package com.example.ToDoList.service;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public List<Task> findById(int id) {
        return null;
    }

/*    @Override
    public List<Task> findById(int id) {
        return taskRepository.findById(id);
    }*/

    @Override
    public void save(Task task) {
        Comment comm = new Comment();
        Priority priority = new Priority();
        Status status = new Status();
        taskRepository.save(task);
    }
    @Override
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

}