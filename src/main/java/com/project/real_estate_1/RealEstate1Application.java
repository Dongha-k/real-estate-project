package com.project.real_estate_1;

import com.project.real_estate_1.controller.storage.StorageProperties;
import com.project.real_estate_1.controller.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class RealEstate1Application {

	public static void main(String[] args) {
		SpringApplication.run(RealEstate1Application.class, args);
	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
