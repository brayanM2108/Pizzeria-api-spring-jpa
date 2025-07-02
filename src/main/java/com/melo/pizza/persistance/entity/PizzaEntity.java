package com.melo.pizza.persistance.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private double price;

    @Column(columnDefinition = "smallint")
    private boolean vegan;

    @Column(columnDefinition = "smallint")
    private boolean vegetarian;

    @Column(columnDefinition = "smallint", nullable = false)
    private boolean available;

}
