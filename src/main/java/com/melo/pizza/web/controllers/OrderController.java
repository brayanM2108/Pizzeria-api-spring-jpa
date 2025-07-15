package com.melo.pizza.web.controllers;

import com.melo.pizza.persistance.dto.RandomOrderDto;
import com.melo.pizza.persistance.entity.OrderEntity;
import com.melo.pizza.persistance.projection.OrderSummary;
import com.melo.pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {

      return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders (@PathVariable String id) {
        List<OrderEntity> orders = orderService.getCustomerOrders(id);
        return orders.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(orders);
    }

    @GetMapping("/summary/{orderId}")
    public ResponseEntity<OrderSummary> getOrderSummary(@PathVariable int orderId) {
        OrderSummary summary = this.orderService.getOrderSummary(orderId);
        return summary != null
                ? ResponseEntity.ok(summary)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> RandomOrder(@RequestBody RandomOrderDto randomOrderDto) {
        return ResponseEntity.ok(this.orderService.saveRandomOrder(randomOrderDto));
    }

}
