package com.demo.spring.microservice.order.repositories;

import com.demo.spring.microservice.order.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
