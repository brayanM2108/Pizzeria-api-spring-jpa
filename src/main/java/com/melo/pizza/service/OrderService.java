package com.melo.pizza.service;

import com.melo.pizza.persistance.dto.RandomOrderDto;
import com.melo.pizza.persistance.entity.OrderEntity;
import com.melo.pizza.persistance.projection.OrderSummary;
import com.melo.pizza.persistance.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.orderRepository.findAll();
        orders.forEach(order -> System.out.println(order.getCustomer().getName()));
        return orders;
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime date = LocalDate.of(2025,6,26).atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(date);
    }
    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders (String idCustomer){
        List<OrderEntity> orders = orderRepository.findCustomerOrders(idCustomer);
        if (orders.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron órdenes para el cliente: " + idCustomer);
        }
        return orders;
    }

    public OrderSummary getOrderSummary(int orderId) {
        return this.orderRepository.findSummary(orderId);
        }


    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());

    }
}
