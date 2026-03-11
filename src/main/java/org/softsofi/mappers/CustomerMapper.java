package org.softsofi.mappers;

import org.mapstruct.Mapper;
import org.softsofi.dto.CustomerDto;
import org.softsofi.entities.Customer;

@Mapper(componentModel = "cdi")
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);
}
