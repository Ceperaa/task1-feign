package ru.clevertec.task1feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Task1FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task1FeignApplication.class, args);
	}

}
