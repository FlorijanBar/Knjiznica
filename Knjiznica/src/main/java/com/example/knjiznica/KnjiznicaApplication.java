package com.example.knjiznica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
@SpringBootApplication
public class KnjiznicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnjiznicaApplication.class, args);
	}

}
