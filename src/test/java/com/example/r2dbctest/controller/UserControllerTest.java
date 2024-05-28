package com.example.r2dbctest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerTest extends ContainerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void test01() {
        webTestClient.get().uri("/users").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
    }
}
