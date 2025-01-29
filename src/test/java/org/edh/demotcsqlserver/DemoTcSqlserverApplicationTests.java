package org.edh.demotcsqlserver;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class DemoTcSqlserverApplicationTests extends TestcontainersConfiguration {

    @LocalServerPort
    private Integer port;

    @PostConstruct
    private void configure() {
        RestAssured.port = port;
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    UsersClient usersClient;

    @Test
    void findUser_getApiUser() {
        RestAssured.given()
                .when()
                .request("GET", "/users/1")
                .then().assertThat()
                .statusCode(200)
                .body("username", Matchers.equalTo("username1"));
        wireMock.verifyThat(1, WireMock.getRequestedFor(WireMock.urlEqualTo("/users/1")));

    }


    @Test
    void findUser_getDBUser() {
        RestAssured.given()
                .when()
                .request("GET", "/users/2")
                .then().assertThat()
                .statusCode(200)
                .body("username", Matchers.equalTo("user2"));
    }

    @Test
    void findUser_withInvalidUserId_returnError400() {
        RestAssured.given()
                .when()
                .request("GET", "/users/abc")
                .then().assertThat()
                .statusCode(400)
                .body("desc", Matchers.equalTo("Bad request"));
    }

}
