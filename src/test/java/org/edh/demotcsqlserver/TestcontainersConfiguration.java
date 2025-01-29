package org.edh.demotcsqlserver;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.utility.DockerImageName;
import org.wiremock.integrations.testcontainers.WireMockContainer;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    public static WireMockContainer wireMockContainer = new WireMockContainer("wiremock/wiremock")
            .withClasspathResourceMapping("wiremock", "/home/wiremock", BindMode.READ_ONLY);

    public static MSSQLServerContainer<?> sqlServerContainer = new MSSQLServerContainer<>(DockerImageName.parse("mcr.microsoft.com/mssql/server:latest"))
            .acceptLicense();

    protected WireMock wireMock = WireMock.create().port(wireMockContainer.getPort())
            .build();

    static {
        wireMockContainer.start();
        sqlServerContainer.start();
        System.setProperty("spring.profiles.active", "local");
        System.setProperty("test.url", wireMockContainer.getBaseUrl() + "/users");
        System.setProperty("spring.datasource.url", sqlServerContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", sqlServerContainer.getUsername());
        System.setProperty("spring.datasource.password", sqlServerContainer.getPassword());

    }

}
