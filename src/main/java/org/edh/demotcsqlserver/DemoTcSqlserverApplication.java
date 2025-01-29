package org.edh.demotcsqlserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DemoTcSqlserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTcSqlserverApplication.class, args);
    }

}
