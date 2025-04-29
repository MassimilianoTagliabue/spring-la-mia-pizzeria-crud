package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    
    @Autowired
    private OfferRepository offerRepository;

    public Offer getById(Integer id){
        return offerRepository.findById(id).get();
    }

    public Offer createOrUpdate(Offer offer){
        return offerRepository.save(offer);
    }
}
