package org.softsofi.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.softsofi.dto.CustomerDto;
import org.softsofi.entities.*;
import org.softsofi.exeception.BanckAccountNotFoundExeception;
import org.softsofi.exeception.BlanceNotSufficientExeception;
import org.softsofi.exeception.CustomerNotFoundExeception;
import org.softsofi.mappers.CustomerMapper;
import org.softsofi.repositories.AccountOperationRepository;
import org.softsofi.repositories.BanckAccountRepository;
import org.softsofi.repositories.CustomerRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@ApplicationScoped
@Transactional
public class BanckAccountServiceImp implements BanckAccountService {

    private final   CustomerRepository customerRepository;
    private final BanckAccountRepository banckAccountRepository;
    private final AccountOperationRepository accountOperationService ;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customerRepository.persist(customer);
        ;return  customerMapper.toDto(customer);
    }

    @Override
    public CurrentAccount svaveCurrentBanckAccount(BigDecimal intialBalance, BigDecimal overDraft, Long customerId) throws CustomerNotFoundExeception {
        Customer customer = customerRepository.findById(customerId);
        if (customer == null) throw new CustomerNotFoundExeception("Customer not found");
        CurrentAccount currentAccount = CurrentAccount.builder()
                .id(String.valueOf(System.currentTimeMillis()))
                .balance(intialBalance)
                .createdAt(new Date())
                .overDraft(overDraft)
                .customer(customer)
                .build();
        banckAccountRepository.persist(currentAccount);
        return currentAccount;
    }

    @Override
    public SavingAccount svaveSavingBanckAccount(BigDecimal intialBalance, BigDecimal interestRate, Long customerId) throws CustomerNotFoundExeception {
        Customer customer = customerRepository.findById(customerId);
        if (customer == null) throw new CustomerNotFoundExeception("Customer not found");
        SavingAccount savingAccount = SavingAccount.builder()
                .id(String.valueOf(System.currentTimeMillis()))
                .balance(intialBalance)
                .createdAt(new Date())
                .interestRate(interestRate)
                .customer(customer)
                .build();
        banckAccountRepository.persist(savingAccount);
        return savingAccount;
    }


    @Override
    public List<CustomerDto> listCustomers() {
       List<Customer> customers= customerRepository.listAll();
       return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) throws CustomerNotFoundExeception {
        Customer customer = customerRepository.findById(id);
        if (customer == null) throw new CustomerNotFoundExeception("Customer not found");
        return customerMapper.toDto(customer);
    }
    @Override
    public BanckAccount getBanckAccountById(String accountId) throws BanckAccountNotFoundExeception {
            BanckAccount banckAccount = banckAccountRepository.findById(accountId);
            if (banckAccount == null) throw new BanckAccountNotFoundExeception("Banck account not found");
        return banckAccount;
    }

    @Override
    public void debit(String accountId, BigDecimal amount, String description) throws BanckAccountNotFoundExeception, BlanceNotSufficientExeception {
        BanckAccount banckAccount = getBanckAccountById(accountId);
        if(banckAccount.getBalance().compareTo(amount) < 0) throw new BlanceNotSufficientExeception("Balance not sufficient");
        AccountOperation accountOperation = AccountOperation.builder()
                .operationDate(new Date())
                .amount(amount)
                .operationType(org.softsofi.enums.OperationType.DEBIT)
                .banckAccount(banckAccount)
                .description(description)
                .build();
        accountOperationService.persist(accountOperation);
        banckAccount.setBalance(banckAccount.getBalance().subtract(amount));
        banckAccountRepository.persist(banckAccount);

    }

    @Override
    public void credit(String accountId, BigDecimal amount, String description) throws BanckAccountNotFoundExeception {
        BanckAccount banckAccount = getBanckAccountById(accountId);
        AccountOperation accountOperation = AccountOperation.builder()
                .operationDate(new Date())
                .amount(amount)
                .operationType(org.softsofi.enums.OperationType.CREDIT)
                .banckAccount(banckAccount)
                .description(description)
                .build();
        accountOperationService.persist(accountOperation);
        banckAccount.setBalance(banckAccount.getBalance().add(amount));
        banckAccountRepository.persist(banckAccount);

    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, BigDecimal amount) throws BlanceNotSufficientExeception, BanckAccountNotFoundExeception {

            debit(accountIdSource, amount, "Transfer to " + accountIdDestination);
            credit(accountIdDestination, amount, "Transfer from " + accountIdSource);


    }
    @Override
    public List<BanckAccount> listBanckAccounts() {
        return banckAccountRepository.listAll();
    }
}
