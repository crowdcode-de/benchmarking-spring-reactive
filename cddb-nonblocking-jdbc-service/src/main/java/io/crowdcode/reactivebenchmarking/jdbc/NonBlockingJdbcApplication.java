package io.crowdcode.reactivebenchmarking.jdbc;

import com.github.pgasync.ConnectionPoolBuilder;
import com.github.pgasync.Db;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
