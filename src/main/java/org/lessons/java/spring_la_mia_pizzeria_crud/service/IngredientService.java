package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll(){
        return ingredientRepository.findAll();
    }

    public Ingredient getById(Integer id){
        return ingredientRepository.findById(id).get();
    }

    public Ingredient createOrUpdate(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public void deleteById(Integer id){
        Ingredient deleteIngredient = getById(id);  //ingrediente da cancellare
        
        for (Pizza linkedPizza : deleteIngredient.getPizzas()   ) {     //cicla tutte le pizze con quel'ingrediente
            linkedPizza.getIngredients().remove(deleteIngredient);      //rimuove l'ingrediente collegato
        }

        ingredientRepository.deleteById(id);
    }
}
