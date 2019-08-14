package com.tre.jdevtemplateboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.tre.jdevtemplateboot.mapper")
public class JdevtemplatebootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdevtemplatebootApplication.class, args);
    }
}
