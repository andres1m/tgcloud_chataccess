package ru.andres1m.tgcloud.chataccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		EnvLoader.loadEnv();
		SpringApplication.run(Application.class, args);
	}
}