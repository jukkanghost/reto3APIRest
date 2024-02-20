package com.microcompany.accountsservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql("classpath:datos_prueba.sql")
public class AccountsServiceControllerTestRestTemplate {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void añadirPositivo() throws Exception {
        // given
        Long id = 1L;
        int amount = 20;
        Long ownerId = 1L;

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/accounts/" + id + "/añadir?amount=" + amount + "&ownerId=" + ownerId, HttpMethod.PUT, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
    }

    @Test
    public void añadirNegativo() throws Exception {
        // given
        Long id = 1L;
        int amount = -20;
        Long ownerId = 1L;

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/accounts/" + id + "/añadir?amount=" + amount + "&ownerId=" + ownerId, HttpMethod.PUT, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
    }

    @Test
    public void eliminarPositivo() throws Exception {
        // given
        Long ownerId = 1L;

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/accounts/deleteAll/"+ownerId, HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

     @Test
    public void eliminarNegativo() throws Exception {
        // given
        Long ownerId = -1L;

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port + "/accounts/deleteAll/"+ownerId, HttpMethod.DELETE, null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);
    }
}
