package io.crowdcode.nonblocking.cddb;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientOptionsFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {

    @Value("${mongodb_host:localhost}")
    private String host;

    @Value("${mongodb_databaseName:cddb}")
    private String databaseName;

    @Bean
    public MongoClientOptionsFactoryBean mongoClientOptionsFactoryBean(){
        MongoClientOptionsFactoryBean bean = new MongoClientOptionsFactoryBean();
        bean.setMinConnectionsPerHost(100);
        bean.setConnectionsPerHost(10000);
        return bean;
    }

    @Bean
    public MongoClient mongo(@Autowired MongoClientOptionsFactoryBean mongoClientOptionsFactoryBean) throws Exception {
        return new MongoClient(host, mongoClientOptionsFactoryBean.getObject());
    }

    @Bean
    public MongoTemplate mongoTemplate(@Autowired MongoClientOptionsFactoryBean mongoClientOptionsFactoryBean) throws Exception {
        return new MongoTemplate(mongo(mongoClientOptionsFactoryBean),databaseName);
    }
}
