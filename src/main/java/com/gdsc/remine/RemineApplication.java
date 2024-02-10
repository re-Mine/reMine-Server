package com.gdsc.remine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RemineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemineApplication.class, args);
	}

}
