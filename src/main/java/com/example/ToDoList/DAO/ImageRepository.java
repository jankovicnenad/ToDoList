package com.example.ToDoList.DAO;

import com.example.ToDoList.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    //Ninko komentarisao
}
