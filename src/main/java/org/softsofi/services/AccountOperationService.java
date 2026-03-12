package org.softsofi.services;

import org.softsofi.dto.AccountOperationDto;
import org.softsofi.exeception.BanckAccountNotFoundExeception;
import org.softsofi.exeception.BlanceNotSufficientExeception;

import java.math.BigDecimal;
import java.util.List;

public interface AccountOperationService {
    void debit(String accountId, BigDecimal amount, String description) throws BanckAccountNotFoundExeception, BlanceNotSufficientExeception;
    void credit(String accountId, BigDecimal amount, String description) throws BanckAccountNotFoundExeception;
    void transfer(String accountIdSource, String accountIdDestination, BigDecimal amount) throws BlanceNotSufficientExeception, BanckAccountNotFoundExeception;
    List<AccountOperationDto> listAllAccountOperations(String accountId);
}
