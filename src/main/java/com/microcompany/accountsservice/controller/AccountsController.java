package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.exception.AccountNotfoundException;
import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.persistence.AccountRepository;
import com.microcompany.accountsservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
@Validated
public class AccountsController {

    @Autowired
    AccountService service;

    @GetMapping(value = "")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccounts());
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> create(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(account));
    }

    @DeleteMapping(value = "/deleteAll/{ownerId}")
    public ResponseEntity deleteAll(@PathVariable("ownerId") @Min(1) Long ownerId) {
        service.deleteAccountsUsingOwnerId(ownerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> getOne(@PathVariable("id") @Min(1) Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccount(id));
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> update(@PathVariable("id") @Min(1) Long id, @RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateAccount(id, account));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") @Min(1) Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}/añadir")
    public ResponseEntity<Object> añadir(@PathVariable("id") @Min(1) Long id, @RequestParam Map<String, String> params) {
        int amount = Integer.parseInt(params.get("amount"));
        Long ownerId = Long.parseLong(params.get("ownerId"));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.addBalance(id, amount, ownerId));
    }

    @PutMapping(value = "/{id}/retirar")
    public ResponseEntity<Object> retirar(@PathVariable("id") @Min(1) Long id, @RequestParam(name = "amount", required = true) int amount, @RequestParam(name = "ownerId", required = true) Long ownerId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.withdrawBalance(id, amount, ownerId));
    }

}