package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
    private Integer id;

    @NotBlank(message = "campo obbligatorio")
    @Size( min=1, max=20, message = "il nome può avere massimo 20 caratteri")
    private String name;

    @NotBlank(message = "campo obbligatorio")
    @Lob
    @Size(min=1, message = "inserire almeno un ingrediente")
    private String description;

    @NotBlank(message = "campo obbligatorio")
    private String image;

    @PositiveOrZero(message = "campo obbligatorio")
    @Min(value = 1, message = "il valore non può essere zero")
    private float price;

    // relazione tra una pizza e N sconti
    @OneToMany( mappedBy = "pizza")
    private List<Offer> offers;


    public List<Offer> getOffer() {
        return this.offers;
    }

    public void setOffer(List<Offer> offers) {
        this.offers = offers;
    }
    
    public Pizza() {
    }

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", getName(), getDescription(), getPrice());
    }
}
