package com.microcompany.accountsservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    @RequestMapping("")
    public String hola() {
        return "Hola !!";
    }

}
