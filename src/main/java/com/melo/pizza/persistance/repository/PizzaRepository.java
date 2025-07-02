package com.melo.pizza.persistance.repository;

import com.melo.pizza.persistance.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository <PizzaEntity, Integer> {


}
