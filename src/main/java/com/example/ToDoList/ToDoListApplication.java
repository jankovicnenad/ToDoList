package com.example.ToDoList;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.ResourceUtils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Configuration
@EnableJpaAuditing //allow JPA auditing
@SpringBootApplication
public class ToDoListApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(ToDoListApplication.class, args);
		File file = ResourceUtils.getFile("classpath:serviceAccountKey.json");

		FileInputStream serviceAccount =
				new FileInputStream(file);

		StorageOptions.newBuilder()
				.setProjectId("tasksimage")
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}
}
