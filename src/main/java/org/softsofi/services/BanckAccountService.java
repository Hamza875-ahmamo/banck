package org.softsofi.services;

import org.softsofi.dto.*;
import org.softsofi.entities.BanckAccount;
import org.softsofi.entities.CurrentAccount;
import org.softsofi.entities.Customer;
import org.softsofi.entities.SavingAccount;
import org.softsofi.exeception.BanckAccountNotFoundExeception;
import org.softsofi.exeception.BlanceNotSufficientExeception;
import org.softsofi.exeception.CustomerNotFoundExeception;

import java.math.BigDecimal;
import java.util.List;

public interface BanckAccountService {
    CurrentBanckAccountDto saveCurrentBanckAccount(BigDecimal intialBalance, BigDecimal overDraft, Long customerId) throws CustomerNotFoundExeception;
    SavingAccountDto saveSavingBanckAccount(BigDecimal intialBalance,BigDecimal interestRate,Long customerId) throws CustomerNotFoundExeception;
    BanckAccountDto getBanckAccountById(String accountId) throws BanckAccountNotFoundExeception;
    List<BanckAccountDto> listBanckAccounts();
}
