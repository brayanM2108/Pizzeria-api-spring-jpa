package com.melo.pizza.persistance.repository;

import com.melo.pizza.persistance.dto.UpdatePizzaPriceDTO;
import com.melo.pizza.persistance.entity.PizzaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository <PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    List<PizzaEntity> findCountByAvailableFalse();

    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(BigDecimal price);

    int countByVeganTrue();

    @Query(value = """
                   UPDATE pizza
                   SET price = :#{#newPizzaPrice.newPrice}
                   WHERE id_pizza = :#{#newPizzaPrice.pizzaId}
                    """, nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDTO newPizzaPrice);
}

