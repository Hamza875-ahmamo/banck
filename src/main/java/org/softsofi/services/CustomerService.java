package org.softsofi.services;

import org.softsofi.dto.CustomerDto;
import org.softsofi.exeception.CustomerNotFoundExeception;

import java.util.List;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customer);
    List<CustomerDto> listCustomers();
    CustomerDto getCustomerById(Long id) throws CustomerNotFoundExeception;
    CustomerDto updateCustomer(CustomerDto customer);
    void deleteCustomer(Long id);
}
