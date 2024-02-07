package com.example.springwebclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableCaching
public class SpringWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebClientApplication.class, args);

	}

}
