package com.adriannavarrogabino.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringbootServicioServicioOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioServicioOauthApplication.class, args);
	}

}
