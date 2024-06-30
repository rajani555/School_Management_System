package com.abhikarma_rajani;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
public class MvcAbhikarmaApplication extends SpringServletContainerInitializer
{
	public static void main(String[] args)
	{
		SpringApplication.run(MvcAbhikarmaApplication.class, args);
	}

}
