package org.softsofi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.softsofi.enums.AccountStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE",length = 4,discriminatorType = DiscriminatorType.STRING)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BanckAccount {
    @Id
    private String id;
    private BigDecimal balance;
    private Date createdAt;
    @ManyToOne
    private Customer customer;
    private AccountStatus status;
    @OneToMany(mappedBy = "banckAccount",fetch = FetchType.LAZY)
    private List<AccountOperation> operations;
}
