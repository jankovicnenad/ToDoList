package com.example.ToDoList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class ToDoListApplication {

	public static void main(String[] args) throws FileNotFoundException {

		FileInputStream serviceAccount =
				new FileInputStream("path/to/serviceAccountKey.json");

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		FirebaseApp.initializeApp(options);

		SpringApplication.run(ToDoListApplication.class, args);}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}
}
