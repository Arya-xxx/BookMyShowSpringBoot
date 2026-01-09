package com.example.bookmyshow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Entity
@Table(name = "Theatre")
@Getter
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Theatre name is required")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    // Controlled constructor → business-safe creation
    public Theatre(String name, City city){
        this.name =name;
        this.city = city;
    }
    // Optional: business method instead of setter
    public void updateName(String name) {
        this.name = name;
    }

    public void changeCity(City city) {
        this.city = city;
    }

}
