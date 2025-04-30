package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired 
    private IngredientRepository ingredientRepository;


    public List<Pizza> findAll(){
       return pizzaRepository.findAll();
    }

    public Pizza getById(Integer id){
        return pizzaRepository.findById(id).get();
    }

    public Optional<Pizza> findById(Integer id){
        return pizzaRepository.findById(id);
    }

    public List<Pizza> findByName(String name){
        return pizzaRepository.findByNameContaining(name);
    }

    public Pizza createOrUpdate(Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza){
        
        for (Offer offerToDelete : pizza.getOffer()) {
            offerRepository.delete(offerToDelete);
        }

        pizzaRepository.delete(pizza);;
    }

    public void deleteById(Integer id){
        Pizza pizzaToDelete= getById(id);
        
        for (Offer offerToDelete : pizzaToDelete.getOffer()) {
            offerRepository.delete(offerToDelete);
        }

        pizzaRepository.delete(pizzaToDelete);;
    }

    public List<Ingredient> findAllIngredients(){
        return ingredientRepository.findAll();
    }

}
