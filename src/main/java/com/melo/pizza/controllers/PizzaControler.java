package com.melo.pizza.controllers;

import com.melo.pizza.persistance.entity.PizzaEntity;
import com.melo.pizza.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/pizzas")
public class PizzaControler {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaControler(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity <PizzaEntity> get(@PathVariable int idPizza){
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
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
