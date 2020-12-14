package com.vapasi.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/biblioteca/**"))
				.apis(RequestHandlerSelectors.basePackage("com.vapasi"))
				.build().apiInfo(apiDetails());
	}

	public ApiInfo apiDetails(){
		return new ApiInfo(
				"Biblioteca API",
				"API for Library Management",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("[Geetika,Neha,Ramya,Rathipriya,Sakthe]","http://vapasi.com","vapasi@thoughtworks.com"),
				"API License",
				"http://vapasi.com",
				Collections.emptyList());

	}

}
