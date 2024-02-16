package com.microcompany.accountsservice.model;

import com.microcompany.accountsservice.constrains.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
@XmlRootElement
@Schema(name = "Account Model", description = "Account of client")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    @Schema(name = "Account id", description = "Identifier of account", example = "4")
    private Long id;

    @NotBlank
    @AccountType
    private String type;

    @DateTimeFormat
    Date openingDate;

    @Min(0)
    private int balance;

    @Min(1)
    private Long ownerId;

    @Transient
    Customer owner;

    public Account(String type, int balance, Long ownerId) {
        this.type = type;
        this.balance = balance;
        this.ownerId = ownerId;
    }
}
