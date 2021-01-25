package com.demo.spring.microservice.order.services;

import com.demo.spring.microservice.order.models.Order;
import com.demo.spring.microservice.order.repositories.OrderRepository;
import com.demo.spring.microservice.order.value_objects.Customer;
import com.demo.spring.microservice.order.value_objects.CustomerOrderVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    private final String SPRING_CUSTOMER_URL = "http://" + System.getenv("SPRING_CUSTOMER_URL") + ":9091"; // or http://localhost:9091   
    private WebClient webClientCustomer = WebClient.create(SPRING_CUSTOMER_URL);

    public CustomerOrderVO getOrderWithCustomer(Integer orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            return null;
        }

        Integer customerId = order.getCustomerId();

        Mono<Customer> result = webClientCustomer.get().uri("/api/customers/" + customerId.toString()).retrieve()
                .bodyToMono(Customer.class);

        Customer customer = result.block();

        if (customer == null) {
            return null;
        }

        CustomerOrderVO customerOrderVO = new CustomerOrderVO();
        customerOrderVO.setCustomer(customer);
        customerOrderVO.setOrder(order);

        return customerOrderVO;
    }

    public Order createOrder(Order order) {

        return orderRepository.save(order);
    }
}
