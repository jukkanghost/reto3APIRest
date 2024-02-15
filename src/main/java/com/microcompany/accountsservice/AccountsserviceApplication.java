package com.microcompany.accountsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class AccountsserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsserviceApplication.class, args);
	}

}
