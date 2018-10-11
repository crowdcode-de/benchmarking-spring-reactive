package io.crowdcode.benchmarking.blocking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlockingJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockingJdbcApplication.class, args);
	}
}
