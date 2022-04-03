package com.example.demo;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fabhotels.controllers.UserController;

@SpringBootApplication
@ComponentScan("com.fabhotels.*")
@EntityScan("com.fabhotels.entity")
@EnableJpaRepositories("com.fabhotels.repository")
public class FabHotelsApplication {

	static Logger log = Logger.getLogger(UserController.class.getName());
	public static void main(String[] args) {
		log.info("----------Spring Boot Started class !!!!----------");
		SpringApplication.run(FabHotelsApplication.class, args);
	}
}
