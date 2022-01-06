package com.udec.demo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class FicherosServiciosApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Test
	void contextLoads() {
	}

	
	@Test
	void encriptarClave() {
		System.out.println("Clave 1:::::::::::::::..."+ bcrypt.encode("1234"));
		System.out.println("Clave 2:::::::::::::::..."+ bcrypt.encode("4321"));
		assertTrue(true);
	}
	
}
