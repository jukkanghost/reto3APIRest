package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.persistence.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql("classpath:datos_prueba.sql")
public class AccountsServiceControllerTest_MockMvc {

    @Autowired
    private AccountRepository repo;

    @Autowired
    MockMvc mvc;

    @Test
    public void añadirPositivo() throws Exception {
        // given
         Long id = 1L;
        int amount = 20;
        Long ownerId = 1L;

        // when - then
        mvc.perform(put("/accounts/"+id+"/añadir?amount="+amount+"&ownerId="+ownerId).accept(MediaType.APPLICATION_JSON_VALUE))
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
        mvc.perform(put("/accounts/"+id+"/añadir?amount="+amount+"&ownerId="+ownerId).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isPreconditionFailed())
        ;

    }

    @Test
    public void eliminarPositivo() throws Exception {
        // given
        Long ownerId = 1L;

        // when - then
        mvc.perform(delete("/accounts/deleteAll/"+ownerId).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
        ;

    }

     @Test
    public void eliminarNegativo() throws Exception {
        // given
        Long ownerId = -1L;

        // when - then
        mvc.perform(delete("/accounts/deleteAll/"+ownerId).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isPreconditionFailed())
        ;

    }
}
