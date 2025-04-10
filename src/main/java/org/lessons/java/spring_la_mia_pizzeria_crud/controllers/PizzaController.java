package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;
    
    //homepage
    @GetMapping("/pizza")
    public String index(Model model) {
        List <Pizza> pizzas = repository.findAll(); //select * from pizza

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    //detail page
    @GetMapping("/pizza/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Pizza pizza = repository.findById(id).get(); //select * from pizza where id=id
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }
    
    //searchbar
    @GetMapping("/pizza/search")
    public String findPizza(@RequestParam (name = "name") String name , Model model) {
        List <Pizza> pizzas = repository.findByNameContaining(name); // select * from pizza where name like %
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    //create page
    @GetMapping("/pizza/create")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/pizza/create")
    public String store (@Valid @ModelAttribute ("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "pizzas/create";
        }
        repository.save(formPizza);
        return "redirect:/pizza";
    }
    
    //update page
    @GetMapping("/pizza/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());

        return "pizzas/edit";
    }

    @PostMapping("/pizza/edit/{id}")
    public String update(@Valid @ModelAttribute ("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "pizzas/edit";
        }

        repository.save(formPizza);
        
        return "redirect:/pizza";
    }
    
    //delete
    @PostMapping("/pizza/delete/{id}")
    public String postMethodName(@PathVariable Integer id) {
        repository.deleteById(id);
        
        return "redirect:/pizza";
    }
    
    
}
