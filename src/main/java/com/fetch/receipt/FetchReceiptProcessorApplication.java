package com.fetch.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FetchReceiptProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FetchReceiptProcessorApplication.class, args);
	}

}
