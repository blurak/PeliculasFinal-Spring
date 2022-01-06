package com.udec.demo;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@SpringBootApplication
@ComponentScan("com.udec")
@EntityScan(basePackages= {"com.udec.entity","com.udec.view"})
@EnableJpaRepositories("com.udec.repository")
public class FicherosServiciosApplication extends SpringBootServletInitializer{

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(FicherosServiciosApplication.class, args);
		
	}

}
