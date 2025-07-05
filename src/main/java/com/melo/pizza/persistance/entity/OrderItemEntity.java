package com.melo.pizza.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idItem;


    @Column(name = "id_pizza")
    private Integer idPizza;

    @Column(nullable = false, precision = 2, scale = 1)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_order", referencedColumnName = "id_order", insertable = false, updatable = false)
    @JsonIgnore
    private OrderEntity order;

    @OneToOne
    @JoinColumn(name = "id_pizza", referencedColumnName ="id_pizza",insertable = false, updatable = false)
    private PizzaEntity pizzaId;
}
