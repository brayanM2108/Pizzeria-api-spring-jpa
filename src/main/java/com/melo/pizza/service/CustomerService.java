package com.melo.pizza.service;

import com.melo.pizza.persistance.entity.CustomerEntity;
import com.melo.pizza.persistance.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhoneNumber(String phone) {
        return this.customerRepository.findByPhoneNumber(phone);
    }
}
