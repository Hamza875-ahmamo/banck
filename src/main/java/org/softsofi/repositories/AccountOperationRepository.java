package org.softsofi.repositories;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

import org.softsofi.entities.AccountOperation;

@ApplicationScoped
public class AccountOperationRepository implements PanacheRepositoryBase<AccountOperation,Long> {
    public List<AccountOperation> findByAccountId(String accountId){
        return list("banckAccount.id", accountId);
    }
}

