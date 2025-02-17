package com.revature.Project1Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.revature.Models") //Tells spring to scan this package for DB entities
@ComponentScan("com.revature") // Tells spring to scan this package for beans
@EnableJpaRepositories("com.revature.DAOs") // Enables JPA repositories in our DAOs package
public class ProjectOneBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOneBackendApplication.class, args);
	}

}
