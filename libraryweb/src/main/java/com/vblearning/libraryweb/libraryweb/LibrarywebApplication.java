package com.vblearning.libraryweb.libraryweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LibrarywebApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarywebApplication.class, args);
	}

}
