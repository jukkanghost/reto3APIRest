package com.microcompany.accountsservice.controller;

import com.microcompany.accountsservice.model.Account;
import com.microcompany.accountsservice.persistence.AccountRepository;
import com.microcompany.accountsservice.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
//@CrossOrigin(origins = {"*"}, allowedHeaders = "*")
public class AccountsController {

    @Autowired
    AccountService service;


    @Operation(summary = "Get all accounts", description = "Returns a list of accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Empty")
    })
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
        if (ownerId != null && ownerId > 0L) {
            service.deleteAccountsUsingOwnerId(ownerId);
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
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
    public ResponseEntity<Object> añadir(@PathVariable("id") @Min(1) Long id, @RequestParam(name = "amount", required = true) int amount, @RequestParam(name = "ownerId", required = true) Long ownerId) {
        if (id != null && id > 0L && amount >= 0 && ownerId != null && ownerId > 0L) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.addBalance(id, amount, ownerId));
        } else return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
    }

    @PutMapping(value = "/{id}/retirar")
    public ResponseEntity<Object> retirar(@PathVariable("id") @Min(1) Long id, @RequestParam(name = "amount", required = true) int amount, @RequestParam(name = "ownerId", required = true) Long ownerId) {
        if (id != null && id > 0L && amount >= 0 && ownerId != null && ownerId > 0L) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.withdrawBalance(id, amount, ownerId));
        } else return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
    }

}
