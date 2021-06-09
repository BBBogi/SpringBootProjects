package com.claim.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.claim.app.repository")
@EntityScan(basePackages = {"com.claim.app.entity"}) 
public class ClaimRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimRestApiApplication.class, args);
	}

}
