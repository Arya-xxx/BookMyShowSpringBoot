package com.example.bookmyshow.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Getter
@Table(name = "screens")
public class Screen {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;
    public  Screen(String name, Theatre theatre){
        this.name = name;
        this.theatre = theatre;
    }
    protected Screen(){}
    public void updateName(String name){
        this.name = name;
    }
    public void updateTheatre(Theatre theatre){
        this.theatre = theatre;
    }

}
