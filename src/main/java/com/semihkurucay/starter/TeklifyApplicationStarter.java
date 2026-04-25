package com.semihkurucay.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.semihkurucay"})
@EntityScan(basePackages = {"com.semihkurucay"})
@ComponentScan(basePackages = {"com.semihkurucay"})
@SpringBootApplication
public class TeklifyApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(TeklifyApplicationStarter.class, args);
	}

}
