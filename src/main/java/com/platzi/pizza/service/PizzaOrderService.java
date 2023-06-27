package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaOrderEntity;
import com.platzi.pizza.persistence.projection.OrderSummary;
import com.platzi.pizza.persistence.repository.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class PizzaOrderService {
    private final PizzaOrderRepository pizzaOrderRepository;
    private static final String DELIVERY = "D";
    private static final String CARRYOUT = "C";
    private static final String ON_SITE = "S";
    @Autowired
    public PizzaOrderService(PizzaOrderRepository pizzaOrderRepository){
        this.pizzaOrderRepository = pizzaOrderRepository;
    }
    public List<PizzaOrderEntity> getAll(){
        List<PizzaOrderEntity> orders = this.pizzaOrderRepository.findAll();
        orders.forEach(o -> System.out.println(o.getCustomer().getName()));
        return orders;
    }
    public List<PizzaOrderEntity> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return this.pizzaOrderRepository.findAllByDateAfter(today);
    }
    public List<PizzaOrderEntity> getOutsideOrders(){
        List<String> methods = Arrays.asList(DELIVERY,CARRYOUT);
        return this.pizzaOrderRepository.findAllByMethodIn(methods);
    }
    public List<PizzaOrderEntity> getCustomerOrders(String idCustomer){
        return this.pizzaOrderRepository.findCustomerOrders(idCustomer);
    }
    public OrderSummary getSummary(int orderId){
        return this.pizzaOrderRepository.findSummary(orderId);
    }
}
