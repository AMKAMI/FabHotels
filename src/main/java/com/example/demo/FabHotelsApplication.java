package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.fabhotels.*")
@EntityScan("com.fabhotels.entity")
@EnableJpaRepositories("com.fabhotels.repository")
public class FabHotelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabHotelsApplication.class, args);
	}
}
