package com.thymleaf.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.thymleaf.example.app.repository" })
@EntityScan(basePackages = { "com.thymleaf.example.app.model" })
@ComponentScan(basePackages = { "com.thymleaf.example.app.security", "com.thymleaf.example.app.controller",
		"com.thymleaf.example.app.service", "com.thymleaf.example.app.dao" })
public class ThymleafExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymleafExampleApplication.class, args);
	}

}
