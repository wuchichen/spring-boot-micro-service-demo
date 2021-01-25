package com.demo.spring.microservice.customer.repositories;

import com.demo.spring.microservice.customer.models.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
