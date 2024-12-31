package com.devsuperior.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Testando o encoder de senha
		// out: BCRYPT = $2a$10$r702XXbiOGy6CjuIjyUd2.Lzm2pgbrJjBsut/YfncWM6YSrpmD5Ii
		System.out.println("BCRYPT = " + passwordEncoder.encode("123456"));

		// Testando o método matches para verificar se a senha tem mesmo o hash
		// out: MATCHES = true
		boolean matches = passwordEncoder.matches("123456", "$2a$10$r702XXbiOGy6CjuIjyUd2.Lzm2pgbrJjBsut/YfncWM6YSrpmD5Ii");
		System.out.println("MATCHES = " + matches);

	}
}
