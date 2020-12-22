package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
public class DbpmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbpmsAdminApplication.class, args);
    }

}
