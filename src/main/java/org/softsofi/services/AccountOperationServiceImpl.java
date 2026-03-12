package org.softsofi.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.softsofi.dto.AccountOperationDto;
import org.softsofi.entities.AccountOperation;
import org.softsofi.entities.BanckAccount;
import org.softsofi.exeception.BanckAccountNotFoundExeception;
import org.softsofi.exeception.BlanceNotSufficientExeception;
import org.softsofi.mappers.AccountOperationMapper;
import org.softsofi.repositories.AccountOperationRepository;
import org.softsofi.repositories.BanckAccountRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ApplicationScoped
@Transactional
public class AccountOperationServiceImpl implements AccountOperationService {

    private final BanckAccountRepository banckAccountRepository;
    private final AccountOperationRepository accountOperationRepository;
    private final BanckAccountService banckAccountService;
    private final AccountOperationMapper accountOperationMapper;
    
    @Override
    public void debit(String accountId, BigDecimal amount, String description) throws BanckAccountNotFoundExeception, BlanceNotSufficientExeception {
        BanckAccount banckAccount = banckAccountRepository.findById(accountId);
        if(banckAccount.getBalance().compareTo(amount) < 0) throw new BlanceNotSufficientExeception("Balance not sufficient");
        AccountOperation accountOperation = AccountOperation.builder()
                .operationDate(new Date())
                .amount(amount)
                .operationType(org.softsofi.enums.OperationType.DEBIT)
                .banckAccount(banckAccount)
                .description(description)
                .build();
        accountOperationRepository.persist(accountOperation);
        banckAccount.setBalance(banckAccount.getBalance().subtract(amount));
        banckAccountRepository.persist(banckAccount);
    }

    @Override
    public void credit(String accountId, BigDecimal amount, String description) throws BanckAccountNotFoundExeception {
       BanckAccount banckAccount = banckAccountRepository.findById(accountId);
        AccountOperation accountOperation = AccountOperation.builder()
                .operationDate(new Date())
                .amount(amount)
                .operationType(org.softsofi.enums.OperationType.CREDIT)
                .banckAccount(banckAccount)
                .description(description)
                .build();
        accountOperationRepository.persist(accountOperation);
        banckAccount.setBalance(banckAccount.getBalance().add(amount));
        banckAccountRepository.persist(banckAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, BigDecimal amount) throws BlanceNotSufficientExeception, BanckAccountNotFoundExeception {
        debit(accountIdSource, amount, "Transfer to " + accountIdDestination);
        credit(accountIdDestination, amount, "Transfer from " + accountIdSource);
    }

    @Override
    public List<AccountOperationDto> listAllAccountOperations(String accountId) {
        return accountOperationRepository.findByAccountId(accountId).stream().map(op->accountOperationMapper.toDto(op)).collect(Collectors.toList());    }

    
}
