package com.phearun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PrDynamicWebsiteScraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrDynamicWebsiteScraperApplication.class, args);
	}
}
