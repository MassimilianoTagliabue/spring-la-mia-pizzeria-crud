package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;
    
    @GetMapping("/")
    public String Home() {
        return "home";
    }
    
    @GetMapping("/pizza")
    public String index(Model model) {
        List <Pizza> pizzas = repository.findAll(); //select * from pizza

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }
    
}
