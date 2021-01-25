package com.demo.spring.microservice.order.controllers;

import com.demo.spring.microservice.order.models.Order;
import com.demo.spring.microservice.order.services.OrderService;
import com.demo.spring.microservice.order.value_objects.CustomerOrderVO;

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
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{order_id}")
    public ResponseEntity<CustomerOrderVO> getOrderWithCustomer(@PathVariable("order_id") Integer orderId) {

        CustomerOrderVO customerOrderVO = orderService.getOrderWithCustomer(orderId);

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.set("Content-Type", "application/json");

        if (customerOrderVO != null) {
            return new ResponseEntity<>(customerOrderVO, responseHeaders, 200);
        } else {
            return new ResponseEntity<>(null, responseHeaders, 404);
        }
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
}
