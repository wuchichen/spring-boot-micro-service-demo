package com.demo.spring.microservice.customer.services;

import com.demo.spring.microservice.customer.models.Customer;
import com.demo.spring.microservice.customer.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(Integer customer_id) {
        return customerRepository.findById(customer_id).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
