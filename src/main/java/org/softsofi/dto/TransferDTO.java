package org.softsofi.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferDTO {
    private String accountIdSource;
    private String accountIdDestination;
    private BigDecimal amount;
}
