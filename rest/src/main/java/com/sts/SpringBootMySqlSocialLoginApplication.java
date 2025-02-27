package com.sts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootMySqlSocialLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMySqlSocialLoginApplication.class, args);
    }

}
