package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    
    @Autowired
    private IngredientService ingredientService;

    //index
    @GetMapping("")
    public String index(Model model){
        List<Ingredient> ingredienti = ingredientService.findAll();
        model.addAttribute("ingredients", ingredienti);
        return "ingredients/index";
    }

    //create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute ("ingredient") Ingredient formIngredient, 
        BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "/ingredients/create-or-edit";
        }

        ingredientService.createOrUpdate(formIngredient);
        
        return "redirect:/ingredient";
    }


    //edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientService.getById(id));
        model.addAttribute("edit", true);
        return "ingredients/create-or-edit";
    }
    
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute ("ingredient") Ingredient formIngredient, 
    BindingResult bindingResult, Model model) {
        
        if(bindingResult.hasErrors()){
            return "ingredients/create-or-edit";
        }

        ingredientService.createOrUpdate(formIngredient);
        return "redirect:/ingredient";
    }
    
    //delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        ingredientService.deleteById(id);
        return "redirect:/ingredient";
    }
    

    

}


