package com.melo.pizza.persistance.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDTO {

    private int pizzaId;
    private double newPrice;
}
