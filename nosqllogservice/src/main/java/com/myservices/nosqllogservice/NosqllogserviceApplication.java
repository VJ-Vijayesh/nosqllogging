package com.myservices.nosqllogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NosqllogserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NosqllogserviceApplication.class, args);
	}

}
