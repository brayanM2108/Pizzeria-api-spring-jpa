package com.melo.pizza.persistance.entity;


import com.melo.pizza.persistance.audit.AuditPizzaListener;
import com.melo.pizza.persistance.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "pizza")
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @Column(columnDefinition = "smallint")
    private boolean vegan;

    @Column(columnDefinition = "smallint")
    private boolean vegetarian;

    @Column(columnDefinition = "smallint", nullable = false)
    private boolean available;

    @Override
    public String toString() {
        return "PizzaEntity{" +
               "idPizza=" + idPizza +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", vegan=" + vegan +
               ", vegetarian=" + vegetarian +
               ", available=" + available +
               '}';
    }
}
