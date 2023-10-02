package com.stephen.careTrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.stephen.careTrack.model") // Package containing entity classes
@EnableJpaRepositories(basePackages = "com.stephen.careTrack.repository") // Package containing repository interfaces
public class CareTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareTrackApplication.class, args);
	}

}
