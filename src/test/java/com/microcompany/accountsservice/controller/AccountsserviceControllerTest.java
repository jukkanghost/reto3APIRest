package com.microcompany.accountsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles({"test"})
@Sql("classpath:datos_prueba.sql")
public class AccountsserviceControllerTest {

    @Autowired
    private AccountsController controller;
}
