package org.softsofi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import org.softsofi.enums.AccountStatus;

import lombok.Data;
@Data
public class CurrentBanckAccountDto extends BanckAccountDto{
    private String id;
    private BigDecimal balance;
    private LocalDate createdAt;
    private CustomerDto customer;
    private AccountStatus status;
    private BigDecimal overDraft;

}
