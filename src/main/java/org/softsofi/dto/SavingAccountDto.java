package org.softsofi.dto;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import org.softsofi.enums.AccountStatus;

@Data
public class SavingAccountDto extends BanckAccountDto{
    private String id;
    private BigDecimal balance;
    private LocalDate createdAt;
    private CustomerDto customer;
    private AccountStatus status;
    private BigDecimal interestRate ;
}
