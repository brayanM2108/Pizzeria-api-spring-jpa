package com.melo.pizza.web.controllers;

import com.melo.pizza.persistance.dto.UpdatePizzaPriceDTO;
import com.melo.pizza.persistance.entity.PizzaEntity;
import com.melo.pizza.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam (defaultValue = "0") int page,
                                                    @RequestParam (defaultValue = "8") int elements) {
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity <PizzaEntity> get(@PathVariable int idPizza){
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }

    @GetMapping("/availableV2")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam (defaultValue = "0") int page,
                                                          @RequestParam (defaultValue = "8") int elements,
                                                          @RequestParam (defaultValue = "price") String sortBy,
                                                          @RequestParam (defaultValue = "ASC") String sortDirection) {

        return ResponseEntity.ok(this.pizzaService.getAvailableV2(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getAvailableAndByName(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.getAvailableAndByName(name));
    }

    @GetMapping("/with/{description}")
    public ResponseEntity <List<PizzaEntity>> getByName(@PathVariable String description){
        return ResponseEntity.ok(this.pizzaService.getWithDescription(description));
    }

    @GetMapping("/without/{description}")
    public ResponseEntity <List<PizzaEntity>> getWithoutName(@PathVariable String description){
        return ResponseEntity.ok(this.pizzaService.getWithoutDescription(description));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity <List<PizzaEntity>> getCheapest(@PathVariable BigDecimal price){
        return ResponseEntity.ok(this.pizzaService.getCheapestPizzas(price));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PizzaEntity pizza){
        try {
            if (pizza.getIdPizza() == null || !this.pizzaService.exist(pizza.getIdPizza())) {
                return ResponseEntity.ok(this.pizzaService.save(pizza));
            }return ResponseEntity.status(HttpStatus.CONFLICT).body("La pizza ya existe");
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PizzaEntity pizzaEntity){
        try {
            if (pizzaEntity.getIdPizza() != null && this.pizzaService.exist(pizzaEntity.getIdPizza())) {
                return ResponseEntity.ok(this.pizzaService.save(pizzaEntity));
            }return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pizza no existe");
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDTO dto){
        if (this.pizzaService.exist(dto.getPizzaId())) {
            this.pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<?> delete(@PathVariable int idPizza){
        try {
            if (this.pizzaService.exist(idPizza)) {
                this.pizzaService.delete(idPizza);
                return ResponseEntity.ok().build();
            }return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pizza no existe");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
