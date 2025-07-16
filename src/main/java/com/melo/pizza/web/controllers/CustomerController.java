package com.melo.pizza.web.controllers;

import com.melo.pizza.persistance.entity.CustomerEntity;
import com.melo.pizza.persistance.entity.OrderEntity;
import com.melo.pizza.service.CustomerService;
import com.melo.pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> getByPhone(@PathVariable String phone){
        CustomerEntity customer = this.customerService.findByPhoneNumber(phone);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders (@PathVariable String id) {
        List<OrderEntity> orders = orderService.getCustomerOrders(id);
        return orders.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(orders);
    }
}
