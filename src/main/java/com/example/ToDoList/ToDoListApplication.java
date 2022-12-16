package com.example.ToDoList;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
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

	public static void main(String[] args) throws IOException {
//		ClassLoader classLoader = ToDoListApplication.class.getClassLoader();
//
//		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
//		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
//
//		FirebaseOptions options = new FirebaseOptions.Builder()
//				.setCredentials(GoogleCredential.fromStream(serviceAccount))
//				.setDatabaseUrl("")
//				.build();
//		FirebaseApp.initializeApp(options);
		SpringApplication.run(ToDoListApplication.class, args);}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}
}
