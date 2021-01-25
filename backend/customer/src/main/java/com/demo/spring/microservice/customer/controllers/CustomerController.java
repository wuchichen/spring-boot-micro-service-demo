package com.demo.spring.microservice.customer.controllers;

import com.demo.spring.microservice.customer.models.Customer;
import com.demo.spring.microservice.customer.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{customer_id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customer_id") Integer customerId) {
        // Get customer
        Customer customer = customerService.getCustomerById(customerId);

        // Set http headers
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        if (customer != null) {
            // 200: Success
            return new ResponseEntity<>(customer, responseHeaders, 200);
        } else {
            // 404: Resource not found
            return new ResponseEntity<>(null, responseHeaders, 404);
        }
    }
}
