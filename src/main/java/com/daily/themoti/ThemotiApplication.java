package com.daily.themoti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ThemotiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThemotiApplication.class, args);
	}

}
