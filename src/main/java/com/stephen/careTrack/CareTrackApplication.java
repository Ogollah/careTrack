package com.stephen.careTrack;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = "com.stephen.careTrack.model") // Package containing entity classes
@EnableJpaRepositories(basePackages = "com.stephen.careTrack.repository") // Package containing repository interfaces
public class CareTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareTrackApplication.class, args);
	}

	@Bean
	public OpenAPI careTrackOpenAPI() {
		Contact contact = new Contact();
		contact.setEmail("kogollah2011@gmail.com@gmail.com");
		contact.setName("Ogolla");
		contact.setUrl("");

		Server localServer = new Server();
		localServer.setUrl("http://localhost:8095");
		localServer.setDescription("Server URL for Local development");

		Server productionServer = new Server();
		productionServer.setUrl("");
		productionServer.setDescription("Server URL in Production");

		License mitLicense = new License()
				.name("MIT License")
				.url("");

		Info info = new Info()
				.title("Care Track REST API")
				.contact(contact)
				.version("1.0")
				.description("This API exposes endpoints to manage patient vitals")
				.license(mitLicense);

		return new OpenAPI()
				.info(info)
				.servers(List.of(localServer, productionServer));
	}

}
