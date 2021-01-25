package com.demo.spring.microservice.order.value_objects;

import com.demo.spring.microservice.order.models.Order;

public class CustomerOrderVO {
    private Customer customer;

    private Order order;

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
