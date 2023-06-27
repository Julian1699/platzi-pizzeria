package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzi.pizza.persistence.repository.PizzaReposotory;
import com.platzi.pizza.service.Exception.EmailApiException;
import com.platzi.pizza.service.dto.UpdatePizzaPriceDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service

public class PizzaService {
private final PizzaReposotory pizzaReposotory;
private final PizzaPagSortRepository pizzaPagSortRepository;
    @Autowired
    public PizzaService(PizzaReposotory pizzaReposotory, PizzaPagSortRepository pizzaPagSortRepository){
        this.pizzaReposotory = pizzaReposotory;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection){
        System.out.println(this.pizzaReposotory.countByVeganTrue());
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);

        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name){
        return this.pizzaReposotory.findFirstByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWith(String ingredient){
       return this.pizzaReposotory.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithOut(String ingredient){
        return this.pizzaReposotory.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(double price){
        return this.pizzaReposotory.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity get (int id){
        return this.pizzaReposotory.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizzaEntity){
        return this.pizzaReposotory.save(pizzaEntity);
    }
    public void delete(int idPizza){
        this.pizzaReposotory.deleteById(idPizza);
    }

    @Transactional(dontRollbackOn = EmailApiException.class)
    public void updatePrice(UpdatePizzaPriceDto dto) {
        this.pizzaReposotory.updatePrice(dto);
        this.sendEmail();
    }

    private void sendEmail() {
        throw new EmailApiException();
    }
    public boolean exists(int idPizza){
        return this.pizzaReposotory.existsById(idPizza);
    }


}
