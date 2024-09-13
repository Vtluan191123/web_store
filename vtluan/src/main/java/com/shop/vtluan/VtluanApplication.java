package com.shop.vtluan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class VtluanApplication {

	public static void main(String[] args) {
		SpringApplication.run(VtluanApplication.class, args);
	}

}
