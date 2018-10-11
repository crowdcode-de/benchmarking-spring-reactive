package io.crowdcode.reactivebenchmarking.jdbc.config;


import lombok.Data;

@Data
public class DBProperties {

    private String host = "localhost";
    private Integer port = 5432;
    private String username = "postgres";
    private String password;
    private String database = "postgres";
    private Integer poolSize = 10000;

}
