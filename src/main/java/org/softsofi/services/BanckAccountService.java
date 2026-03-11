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
    public CustomerDto saveCustomer(CustomerDto customer);
    CurrentAccount svaveCurrentBanckAccount(BigDecimal intialBalance, BigDecimal overDraft, Long customerId) throws CustomerNotFoundExeception;
    SavingAccount svaveSavingBanckAccount(BigDecimal intialBalance,BigDecimal interestRate,Long customerId) throws CustomerNotFoundExeception;
    List<CustomerDto> listCustomers();
    BanckAccount getBanckAccountById(String accountId) throws BanckAccountNotFoundExeception;
    void debit(String accountId, BigDecimal amount, String description) throws BanckAccountNotFoundExeception, BlanceNotSufficientExeception;
    void credit(String accountId, BigDecimal amount, String description) throws BanckAccountNotFoundExeception;
    void transfer(String accountIdSource, String accountIdDestination, BigDecimal amount) throws BlanceNotSufficientExeception, BanckAccountNotFoundExeception;

    List<BanckAccount> listBanckAccounts();
    public CustomerDto getCustomerById(Long id) throws CustomerNotFoundExeception;
}
