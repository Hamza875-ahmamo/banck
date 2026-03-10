package org.softsofi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softsofi.enums.OperationType;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id ;
    private Date operationDate ;
    private BigDecimal amount ;
    private OperationType operationType ;
    @ManyToOne
    private BanckAccount banckAccount ;
    private String description ;

}
