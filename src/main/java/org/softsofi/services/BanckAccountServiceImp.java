package org.softsofi.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.softsofi.dto.BanckAccountDto;
import org.softsofi.dto.CurrentBanckAccountDto;
import org.softsofi.dto.SavingAccountDto;
import org.softsofi.entities.*;
import org.softsofi.exeception.BanckAccountNotFoundExeception;
import org.softsofi.exeception.CustomerNotFoundExeception;
import org.softsofi.mappers.CurrentBanckAccountMapper;
import org.softsofi.mappers.SavingBanckAccountMapper;
import org.softsofi.repositories.BanckAccountRepository;
import org.softsofi.repositories.CustomerRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@ApplicationScoped
@Transactional
public class BanckAccountServiceImp implements BanckAccountService {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final BanckAccountRepository banckAccountRepository;
    private final CurrentBanckAccountMapper currentBanckAccountMapper;
    private final  SavingBanckAccountMapper savingBanckAccountMapper;



    @Override
    public CurrentBanckAccountDto saveCurrentBanckAccount(BigDecimal intialBalance, BigDecimal overDraft, Long customerId) throws CustomerNotFoundExeception {

        Customer customerEntity = customerRepository.findById(customerId);
        if (customerEntity == null) throw new CustomerNotFoundExeception("Customer not found");
        CurrentAccount currentAccount = CurrentAccount.builder()
                .id(String.valueOf(System.currentTimeMillis()))
                .balance(intialBalance)
                .createdAt(LocalDate.now())
                .overDraft(overDraft)
                .customer(customerEntity)
                .build();
                System.out.println("customer: "+currentAccount);
                return currentBanckAccountMapper.toDto(currentAccount);}
          

    @Override
    public SavingAccountDto saveSavingBanckAccount(BigDecimal intialBalance, BigDecimal interestRate, Long customerId) throws CustomerNotFoundExeception {
        Customer customerEntity = customerRepository.findById(customerId);
        if (customerEntity == null) throw new CustomerNotFoundExeception("Customer not found");
        SavingAccount savingAccount = SavingAccount.builder()
                .id(String.valueOf(System.currentTimeMillis()))
                .balance(intialBalance)
                 .createdAt(LocalDate.now())
                .interestRate(interestRate)
                .customer(customerEntity)
                .build();
        banckAccountRepository.persist(savingAccount);
        return savingBanckAccountMapper.toDto(savingAccount);
    }





    @Override
    public BanckAccountDto getBanckAccountById(String accountId) throws BanckAccountNotFoundExeception {
            BanckAccount banckAccount = banckAccountRepository.findById(accountId);
            if (banckAccount == null) throw new BanckAccountNotFoundExeception("Banck account not found");
            if(banckAccount instanceof CurrentAccount){
                return currentBanckAccountMapper.toDto((CurrentAccount) banckAccount);
            }else{
                return savingBanckAccountMapper.toDto((SavingAccount) banckAccount);
            }
        
    }

    @Override
    public List<BanckAccountDto> listBanckAccounts() {
        return banckAccountRepository.listAll().stream().map(banckAccount -> {
            if(banckAccount instanceof CurrentAccount){
                return currentBanckAccountMapper.toDto((CurrentAccount) banckAccount);
            }else{
                return savingBanckAccountMapper.toDto((SavingAccount) banckAccount);
            }
        }).collect(Collectors.toList());
    }
}

