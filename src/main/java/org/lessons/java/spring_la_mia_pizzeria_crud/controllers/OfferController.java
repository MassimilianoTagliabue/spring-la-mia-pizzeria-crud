package org.lessons.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    // create
    @PostMapping("/create")
    public String store(
            @Valid @ModelAttribute("offer") Offer formOffer,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offers/create-or-edit";
        }
        offerService.createOrUpdate(formOffer);

        return "redirect:/pizza";
    }

    // update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("offer", offerService.getById(id));
        model.addAttribute("edit", true);

        return "offers/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid 
            @ModelAttribute("offer") Offer formOffer,
            BindingResult bindingResult,
            Model model) {

                if (bindingResult.hasErrors()) {
                    return "offers/create-or-edit";
                }
        
                offerService.createOrUpdate(formOffer);

        return "redirect:/pizza/" +formOffer.getPizza().getId();
    }

}
