package io.crowdcode.nonblocking.cddb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Ingo Düppe (CROWDCODE)
 */
@SpringBootApplication
@EnableMongoRepositories
 public class NonBlockingMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NonBlockingMongoApplication.class, args);
    }

}
