package io.crowdcode.benchmarking.jdbc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NonBlockingJdbcApplication {

	@Value("${pg.host}")
	private String host;
	@Value("${pg.port}")
	private String port;
	@Value("${pg.username}")
	private String user;
	@Value("${pg.password}")
	private String pass;



	public static void main(String[] args) {
		SpringApplication.run(NonBlockingJdbcApplication.class, args);
	}



}
