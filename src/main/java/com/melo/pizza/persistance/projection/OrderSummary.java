package com.melo.pizza.persistance.projection;

import java.time.LocalDateTime;

public interface OrderSummary {

    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();

}
