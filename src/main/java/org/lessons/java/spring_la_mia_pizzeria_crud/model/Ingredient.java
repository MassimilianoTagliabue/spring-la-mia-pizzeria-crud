package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "campo obbligatorio")
    @Size(min=2, message = "inserire almeno 2 caratteri")
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private List<Pizza> pizzas;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return String.format("%d", name);
    }


}
