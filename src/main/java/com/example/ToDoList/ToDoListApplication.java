package com.example.ToDoList;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;


import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

@Configuration
@EnableJpaAuditing //allow JPA auditing
@OpenAPIDefinition
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

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        System.out.println("Spring boot application running in UTC timezone :" + new Date());
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();

    }
//alternative for liquibase
/*
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.0.39:3306/ToDoList?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("my-secret-password");
		return dataSource;
	}


	@Bean
	public SpringLiquibase liquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.xml");
		liquibase.setDataSource(dataSource());
		return liquibase;
	}

*/

}
