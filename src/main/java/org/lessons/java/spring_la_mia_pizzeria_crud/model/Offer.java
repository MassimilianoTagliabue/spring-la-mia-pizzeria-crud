package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "discount")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "inserire la data di inizio")
    @PastOrPresent(message = "non è possibile inserire una data già trascorsa")
    private LocalDate startDate;

    @NotNull(message = "inserire la data di fine")
    private LocalDate endDate;

    @NotBlank(message = "inserire il titolo")
    @Size(min = 1, max = 50, message = "il titolo deve avere minimo 1 e massimo 30 caratteri")
    private String title;

    // pizza a cui sono collegato
    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    

}
