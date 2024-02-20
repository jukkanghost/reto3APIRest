package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


//@SpringBootTest
//@ActiveProfiles({"test"})
//@Sql("classpath:datos_prueba.sql")
@ExtendWith(SpringExtension.class)
public class AccountsserviceControllerTest {

    @TestConfiguration
    static class AccountsServiceControllerTestContextConfiguration {
        @Bean
        public AccountsController accountsServiceController() {
            return new AccountsController();
        }
    }

    @Autowired
    private AccountsController controller;

    @MockBean
    private AccountService service;


    @BeforeEach
    public void setUp() {
        Account account = new Account("yoquese", 40, 1L);

        Mockito.when(service.addBalance(1L, 20, 1L))
                .thenReturn(account);
    }


    @Test
    public void añadirPositivo() {
        //given
        Long id = 1L;
        int amount = 20;
        Long ownerId = 1L;

        //when
        ResponseEntity<Object> response = controller.añadir(id, amount, ownerId);

        //then
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

    }

    @Test
    public void añadirNegativo() {
        //given
        Long id = -1L;
        int amount = 20;
        Long ownerId = 1L;

        //when
        ResponseEntity<Object> response = controller.añadir(id, amount, ownerId);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);

    }

    @Test
    public void eliminarPositivo() {
        //given
        Long ownerId = 1L;

        //when
        ResponseEntity response = controller.deleteAll(ownerId);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

     @Test
    public void eliminarNegativo() {
        //given
        Long ownerId = -1L;

        //when
        ResponseEntity response = controller.deleteAll(ownerId);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_FAILED);

    }

}
