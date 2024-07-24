package com.springboot.moov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
//import com.springboot.moov.service.job.DataIntegrationJob;

@SpringBootApplication
@EntityScan(basePackages = {"com.springboot.moov.data.entity"})
public class MoovApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoovApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner run(DataIntegrationJob dataIntegrationJob) {
//		return args -> dataIntegrationJob.performDataIntegration();
//	}
}

