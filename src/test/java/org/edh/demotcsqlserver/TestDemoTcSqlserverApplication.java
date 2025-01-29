package org.edh.demotcsqlserver;

import org.springframework.boot.SpringApplication;

public class TestDemoTcSqlserverApplication {

    public static void main(String[] args) {
        SpringApplication.from(DemoTcSqlserverApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
