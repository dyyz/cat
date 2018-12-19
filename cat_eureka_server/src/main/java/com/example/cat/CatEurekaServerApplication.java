package com.example.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CatEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatEurekaServerApplication.class, args);
//		new SpringApplicationBuilder(CatEurekaServerApplication.class).web(true).run(args);
	}
}
