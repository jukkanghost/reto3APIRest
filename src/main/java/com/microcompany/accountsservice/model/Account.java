package com.microcompany.accountsservice.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
@XmlRootElement
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @DateTimeFormat
    Date openingDate;

    private int balance;

    private Long ownerId;

    @Transient
    Customer owner;

    public Account(String type, int balance, Long ownerId) {
        this.type = type;
        this.balance = balance;
        this.ownerId = ownerId;
    }
}
