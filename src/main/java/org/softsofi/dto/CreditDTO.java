package org.softsofi.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreditDTO {
    private String accountId;
    private BigDecimal amount;
    private String description;
}
