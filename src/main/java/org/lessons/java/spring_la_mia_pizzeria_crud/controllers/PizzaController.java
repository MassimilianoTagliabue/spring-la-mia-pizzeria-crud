package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
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
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    // homepage
    @GetMapping("")
    public String index(Model model) {
        List<Pizza> pizzas = repository.findAll(); // select * from pizza

        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    // detail page
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Pizza pizza = repository.findById(id).get(); // select * from pizza where id=id
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }

    // searchbar
    @GetMapping("/search")
    public String findPizza(@RequestParam(name = "name") String name, Model model) {
        List<Pizza> pizzas = repository.findByNameContaining(name); // select * from pizza where name like %
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    // create page
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }
        repository.save(formPizza);
        return "redirect:/pizza";
    }

    // update page
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());

        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult,
            Model model) {
                
        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }

        repository.save(formPizza);

        return "redirect:/pizza";
    }

    // delete
    @PostMapping("/delete/{id}")
    public String postMethodName(@PathVariable Integer id) {
        repository.deleteById(id);

        return "redirect:/pizza";
    }

    // create offer
    @GetMapping("/{id}/offer")
    public String createOffer(@PathVariable Integer id, Model model) {
        Offer offer = new Offer();
        offer.setPizza(repository.findById(id).get());
        model.addAttribute("offer", offer);
        return "offers/create-or-edit";
    }

}
