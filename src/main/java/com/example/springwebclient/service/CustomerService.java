package com.example.springwebclient.service;

import com.example.springwebclient.controller.CustomerRestController;
import com.example.springwebclient.entity.Customer;
import com.example.springwebclient.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Cacheable("customers")
    public List<Customer> getAllCustomers() throws Exception {
        LOGGER.info("Inside the service!");
        List<Customer> allCustomers = customerRepository.findAll();

        if (allCustomers.isEmpty()){
            throw new Exception("No Customers Found!");
        }

        return allCustomers;
    }

    @Cacheable("customerById")
    public Customer getCustomerById(int id) throws Exception {
        LOGGER.info("Inside the service with ID!");
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent())
            return customerOptional.get();

        throw new Exception("Customer Not Found!");
    }

    @CacheEvict(value = {"customers", "customerById"}, allEntries = true)
    public void addCustomer(Customer customer){
        customerRepository.save(customer);
    }

    @CacheEvict(value = {"customers", "customerById"}, allEntries = true)
    public void deleteCustomer(int id) throws Exception {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()){
            customerRepository.deleteById(id);
        }
        else
            throw new Exception("Customer Not Found!");
    }

    @CacheEvict(value = {"customers", "customerById"}, allEntries = true )
    public void updateCustomer(Customer customer) throws Exception {
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        if(customerOptional.isPresent()){
            customer.setId(customerOptional.get().getId());
            customerRepository.save(customer);
        }
        else
            throw new Exception("Customer Not Found!");
    }


}
