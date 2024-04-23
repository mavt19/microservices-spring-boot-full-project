package com.bns.microservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroservicesGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesGatewayApplication.class, args);
	}

}
