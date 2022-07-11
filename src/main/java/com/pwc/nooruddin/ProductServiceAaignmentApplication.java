package com.pwc.nooruddin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info= @Info(title= "product RestAPI",description = "this is RestFul api for Educational purpose"))
public class ProductServiceAaignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceAaignmentApplication.class, args);
	}
	@Bean
	void printer() {
		System.err.println("Please Visit Here For SWAGGER-UI \nhttp://localhost:8080/swagger-ui.html" );
	}
}
