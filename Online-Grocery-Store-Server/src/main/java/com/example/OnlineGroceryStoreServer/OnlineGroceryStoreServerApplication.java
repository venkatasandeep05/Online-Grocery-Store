package com.example.OnlineGroceryStoreServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.OnlineGroceryStoreServer.repo")
public class OnlineGroceryStoreServerApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(OnlineGroceryStoreServerApplication.class, args);
	}

}
