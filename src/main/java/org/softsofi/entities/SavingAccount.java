package org.softsofi.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("SA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SavingAccount extends  BanckAccount{
    private BigDecimal interestRate ;
}
