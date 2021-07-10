package com.project.real_estate_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class RealEstate1Application {

	public static void main(String[] args) {
		SpringApplication.run(RealEstate1Application.class, args);
	}
}
