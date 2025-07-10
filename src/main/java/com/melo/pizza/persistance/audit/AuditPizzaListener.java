package com.melo.pizza.persistance.audit;

import com.melo.pizza.persistance.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;


public class AuditPizzaListener {

    private PizzaEntity currentPizza;

    @PostLoad
    public void postLoad(PizzaEntity pizzaEntity){
        System.out.println("POST LOAD");
        this.currentPizza = SerializationUtils.clone(pizzaEntity);
    }


    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity){
        System.out.println("POST PERSIST/UPDATE");
        System.out.println("OLD VALUE: " + this.currentPizza);
        System.out.println("NEW VALUE: " + pizzaEntity);
    }

    @PreRemove
    public void onPreDelete (PizzaEntity pizzaEntity){
        System.out.println(pizzaEntity);
    }

}
