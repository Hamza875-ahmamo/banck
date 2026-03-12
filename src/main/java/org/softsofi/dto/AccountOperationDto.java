package org.softsofi.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softsofi.enums.OperationType;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class AccountOperationDto {

   private Long id ;
    private Date operationDate ;
    private BigDecimal amount ;
    private OperationType operationType ;
    private String description ;

}
