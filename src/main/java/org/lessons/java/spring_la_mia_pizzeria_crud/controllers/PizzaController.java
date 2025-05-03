package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private PizzaRepository repository;
    
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    // homepage
    @GetMapping("")
    public String index(Authentication authentication, Model model) {
        List<Pizza> pizzas = pizzaService.findAll(); // select * from pizza

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("username", authentication.getName());
        return "pizzas/index";
    }

    // detail page
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Pizza pizza = pizzaService.getById(id); // select * from pizza where id=id

        model.addAttribute("pizza", pizza);
        model.addAttribute("ingredients", pizza.getIngredients());
        return "pizzas/show";
    }

    // searchbar
    @GetMapping("/search")
    public String findPizza(@RequestParam(name = "name") String name, Model model) {
        List<Pizza> pizzas = pizzaService.findByName(name); // select * from pizza where name like %
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    // create page
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", pizzaService.findAllIngredients());
        return "pizzas/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", pizzaService.findAllIngredients());
            return "pizzas/create-or-edit";
        }
        pizzaService.createOrUpdate(formPizza);
        return "redirect:/pizza";
    }

    // update page
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", pizzaService.findAllIngredients());
        model.addAttribute("edit", true);
        return "pizzas/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult,Model model) {
                
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", pizzaService.findAllIngredients());
            return "pizzas/create-or-edit";
        }

        pizzaService.createOrUpdate(formPizza);

        return "redirect:/pizza";
    }

    // delete
    @PostMapping("/delete/{id}")
    public String postMethodName(@PathVariable Integer id) {

        pizzaService.deleteById(id);
        return "redirect:/pizza";
    }


    // create offer
    @GetMapping("/{id}/offer")
    public String createOffer(@PathVariable Integer id, Model model) {
        Offer offer = new Offer();
        offer.setPizza(pizzaService.getById(id));
        model.addAttribute("offer", offer);
        return "offers/create-or-edit";
    }

}
