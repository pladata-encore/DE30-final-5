package com.springboot.moov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.springboot.moov.data.repository")
public class MoovApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoovApplication.class, args);
	}
}
