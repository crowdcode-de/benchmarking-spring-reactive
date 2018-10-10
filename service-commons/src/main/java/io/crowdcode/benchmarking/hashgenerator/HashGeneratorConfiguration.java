package io.crowdcode.benchmarking.hashgenerator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnMissingBean(HashGenerator.class)
@ConditionalOnClass(java.security.MessageDigest.class)
public class HashGeneratorConfiguration {

    @Bean
    public HashGenerator defaultHashGenerator() {
        log.debug("creating DefaultHashGenerator");
        return new DefaultHashGenerator();
    }


}
