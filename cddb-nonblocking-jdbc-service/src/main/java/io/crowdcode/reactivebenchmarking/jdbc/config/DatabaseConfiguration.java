package io.crowdcode.reactivebenchmarking.jdbc.config;

import com.github.pgasync.ConnectionPoolBuilder;
import com.github.pgasync.Db;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "pg")
    public DBProperties dbProperties() {
        return new DBProperties();
    }

    @Bean
    public Db db(DBProperties dbProperties) {
       return new ConnectionPoolBuilder()
                .hostname(dbProperties.getHost())
                .port(Integer.valueOf(dbProperties.getPort()))
                .database(dbProperties.getDatabase())
                .username(dbProperties.getUsername())
                .password(dbProperties.getPassword())
                .poolSize(dbProperties.getPoolSize())
                .build();
    }

}
