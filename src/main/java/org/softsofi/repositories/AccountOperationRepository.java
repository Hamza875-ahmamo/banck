package org.softsofi.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.softsofi.entities.AccountOperation;

@ApplicationScoped
public class AccountOperationRepository implements PanacheRepository<AccountOperation> {
}

