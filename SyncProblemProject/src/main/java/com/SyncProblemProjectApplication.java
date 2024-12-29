package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SyncProblemProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyncProblemProjectApplication.class, args);
		System.out.println("Application up and running on port: 8080");
	}

}
