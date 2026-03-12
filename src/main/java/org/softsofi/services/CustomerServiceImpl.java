package org.softsofi.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.softsofi.dto.CustomerDto;
import org.softsofi.entities.Customer;
import org.softsofi.exeception.CustomerNotFoundExeception;
import org.softsofi.mappers.CustomerMapper;
import org.softsofi.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ApplicationScoped
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customerRepository.persist(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerDto> listCustomers() {
        List<Customer> customers = customerRepository.listAll();
        return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) throws CustomerNotFoundExeception {
        Customer customer = customerRepository.findById(id);
        if (customer == null) throw new CustomerNotFoundExeception("Customer not found");
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customerRepository.getEntityManager().merge(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
