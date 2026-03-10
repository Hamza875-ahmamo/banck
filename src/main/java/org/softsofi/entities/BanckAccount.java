package org.softsofi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softsofi.enums.AccountStatus;

import java.util.Date;
import java.util.List;
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE",length = 4,discriminatorType = DiscriminatorType.STRING)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BanckAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @ManyToOne
    private Customer customer;
    private AccountStatus status;
    @OneToMany(mappedBy = "banckAccount")
    private List<AccountOperation> operations;
}
