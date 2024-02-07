package com.example.springwebclient.controller;

import com.example.springwebclient.entity.Customer;
import com.example.springwebclient.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);

    @GetMapping("/customers")
    @PreAuthorize("hasRole('user')")
    public List<Customer> getCustomers() throws Exception {
        LOGGER.info("The User entered the getCustomers endpoint!");
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{customerId}")
    @PreAuthorize("hasRole('user')")
    public Customer getCustomerById(@PathVariable int customerId) throws Exception {
        LOGGER.info("The User entered the getCustomersByID endpoint!");
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/customers")
    @PreAuthorize("hasRole('manager')")
    public void addCustomer(@RequestBody Customer customer){
        LOGGER.info("The User entered the addCustomer endpoint!");
        customerService.addCustomer(customer);
    }

    @DeleteMapping("/customers/{customerId}")
    @PreAuthorize("hasRole('admin')")
    public void deleteCustomer(@PathVariable int customerId) throws Exception {
        LOGGER.info("The User entered the deleteCustomer endpoint!");
        customerService.deleteCustomer(customerId);
    }

    @PutMapping("/customers")
    @PreAuthorize("hasRole('manager')")
    public void updateCustomer(@RequestBody Customer customer) throws Exception {
        customerService.updateCustomer(customer);
    }
}
