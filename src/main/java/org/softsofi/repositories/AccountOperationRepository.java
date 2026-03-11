package org.softsofi.repositories;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.softsofi.entities.AccountOperation;

@ApplicationScoped
public class AccountOperationRepository implements PanacheRepositoryBase<AccountOperation,Long> {
}

