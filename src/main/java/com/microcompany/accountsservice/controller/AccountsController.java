package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.persistence.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @Autowired
    AccountRepository repo;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> create(@RequestBody Account account){
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(account));
    }

}
