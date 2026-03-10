package org.softsofi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softsofi.enums.OperationType;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id ;
    private Date operationDate ;
    private double amount ;
    private OperationType operationType ;
    @ManyToOne
    private BanckAccount banckAccount ;
}
