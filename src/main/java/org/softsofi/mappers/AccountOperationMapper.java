package org.softsofi.mappers;
import org.mapstruct.Mapper;
import org.softsofi.dto.AccountOperationDto;
import org.softsofi.entities.AccountOperation;
@Mapper(componentModel = "cdi")
public interface AccountOperationMapper {
    AccountOperationDto toDto(AccountOperation accountOperation);
    AccountOperation toEntity(AccountOperationDto accountOperationDto);
}
