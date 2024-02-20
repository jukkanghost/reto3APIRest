package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.persistence.AccountRepository;
import com.microcompany.accountsservice.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountsController.class)
public class AccountsServiceControllerTest_WebMvcTest {

    @BeforeEach
    public void setUp() {
        Account account = new Account("yoquese", 40, 1L);

        Mockito.when(service.addBalance(1L, 20, 1L))
                .thenReturn(account);
    }

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService service;

    @MockBean
    private AccountRepository repository;

    @Test
    public void añadirPositivo() throws Exception {
        // given
        Long id = 1L;
        int amount = 20;
        Long ownerId = 1L;

        // when - then
        mvc.perform(put("/accounts/" + id + "/añadir?amount=" + amount + "&ownerId=" + ownerId).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isAccepted())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.balance", is(equalTo(40))))
        ;

    }

    @Test
    public void añadirNegativo() throws Exception {
        // given
        Long id = 1L;
        int amount = -20;
        Long ownerId = 1L;

        // when - then
        mvc.perform(put("/accounts/" + id + "/añadir?amount=" + amount + "&ownerId=" + ownerId).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isPreconditionFailed())
        ;

    }

    @Test
    public void eliminarPositivo() throws Exception {
        // given
        Long ownerId = 1L;

        // when - then
        mvc.perform(delete("/accounts/deleteAll/" + ownerId).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
        ;

    }

    @Test
    public void eliminarNegativo() throws Exception {
        // given
        Long ownerId = -1L;

        // when - then
        mvc.perform(delete("/accounts/deleteAll/" + ownerId).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isPreconditionFailed())
        ;

    }
}
