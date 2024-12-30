package com.projeto.LiterAlura;

import com.projeto.LiterAlura.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	private final Main main;

	@Autowired
	public LiterAluraApplication(Main main) {
		this.main = main;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws  Exception {
		main.menu();
	}
}
