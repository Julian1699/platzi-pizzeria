package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaOrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import com.platzi.pizza.service.PizzaOrderService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class PizzaOrderController {

    private final PizzaOrderService pizzaOrderService;

    @GetMapping
    public ResponseEntity<List<PizzaOrderEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaOrderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<PizzaOrderEntity>> getTodayOrders(){
        return ResponseEntity.ok(this.pizzaOrderService.getTodayOrders());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<PizzaOrderEntity>> getOutsideOrders(){
        return ResponseEntity.ok(this.pizzaOrderService.getOutsideOrders());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<PizzaOrderEntity>> getOutsideOrders(@PathVariable String id){
        return ResponseEntity.ok(this.pizzaOrderService.getCustomerOrders(id));
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable int id){
        return ResponseEntity.ok(this.pizzaOrderService.getSummary(id));
    }


}
