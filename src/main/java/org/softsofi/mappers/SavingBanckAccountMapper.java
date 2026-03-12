package org.softsofi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.softsofi.dto.SavingAccountDto;
import org.softsofi.entities.SavingAccount;

@Mapper(componentModel = "cdi")
public interface SavingBanckAccountMapper {
    @Mapping(target = "type", constant = "SAVING_ACCOUNT")
    SavingAccountDto toDto(SavingAccount savingAccount);
    SavingAccount toEntity(SavingAccountDto savingAccountDto);
}
