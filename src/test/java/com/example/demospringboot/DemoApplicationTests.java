package com.example.demospringboot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);


    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void testDevProfile() {
        int devPort = devApp.getMappedPort(8080);
        String devUrl = "http://localhost:" + devPort + "/profile";

        ResponseEntity<String> devResponse = restTemplate.getForEntity(devUrl, String.class);
        assertEquals("Current profile is dev", devResponse.getBody());
    }

    @Test
    void testProdProfile() {
        int prodPort = prodApp.getMappedPort(8081);
        String prodUrl = "http://localhost:" + prodPort + "/profile";

        ResponseEntity<String> prodResponse = restTemplate.getForEntity(prodUrl, String.class);
        assertEquals("Current profile is production", prodResponse.getBody());
    }
}
