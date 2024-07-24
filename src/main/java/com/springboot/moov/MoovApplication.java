package com.springboot.moov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.springboot.moov.data.entity"})
public class MoovApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoovApplication.class, args);
	}
}

