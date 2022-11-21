package com.example.ToDoList.service;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository task)
    {taskRepository = task;}

    @Override
    public List<Task> finfAll() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        Comment comm = new Comment();
        task.setComment(comm);
        taskRepository.save(task);
    }

    @Override
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

}
