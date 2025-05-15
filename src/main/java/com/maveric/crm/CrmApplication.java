package com.maveric.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CrmApplication {

	public static void main(String[] args){

	ConfigurableApplicationContext context=  SpringApplication.run(CrmApplication.class, args);

	}
}
