package org.softsofi.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.softsofi.entities.BanckAccount;

@ApplicationScoped
public class BanckAccountRepository implements PanacheRepositoryBase<BanckAccount, String> {
}

