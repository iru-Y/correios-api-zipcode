package com.correios.cep.correioscep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CorreiosCepApplication {
	private static ConfigurableApplicationContext ctx;
	public static void main(String[] args) {
		ctx = SpringApplication.run(CorreiosCepApplication.class, args);
	}
	public static void close (int code){
		SpringApplication.exit(ctx, ()-> code);
	}

}
