package com.example.userservicedemo;

import com.example.userservicedemo.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class UserservicedemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserservicedemoApplication.class, args);
	}
}