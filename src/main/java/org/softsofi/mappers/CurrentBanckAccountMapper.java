package org.softsofi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.softsofi.dto.CurrentBanckAccountDto;
import org.softsofi.entities.CurrentAccount;

@Mapper(componentModel = "cdi")

public interface CurrentBanckAccountMapper {
     @Mapping(target = "type", constant = "CURRENT_ACCOUNT")
    CurrentBanckAccountDto toDto(CurrentAccount currentAccount);
    CurrentAccount toEntity(CurrentBanckAccountDto currentBanckAccountDto);
}

