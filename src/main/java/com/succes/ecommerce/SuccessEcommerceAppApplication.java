package com.succes.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.succes.ecommerce.model"})
public class SuccessEcommerceAppApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SuccessEcommerceAppApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SuccessEcommerceAppApplication.class);
	}

}
