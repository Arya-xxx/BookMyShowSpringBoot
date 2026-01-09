package com.example.bookmyshow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.*;



// Marks this class as a JPA entity (maps to a DB table)
@Entity
@Data
@Table(name = "users")
// Lombok: generates a no-argument constructor -> required by JPA
@NoArgsConstructor

// Lombok: generates constructor with all fields
@AllArgsConstructor

// Lombok: enables Builder pattern for object creation
@Builder
public class User {
    // Marks this field as primary key
    @Id
    //autoincrement
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "email should not be empty")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "name should not be empty")
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @NotNull(message = "Password is required")
    private String password;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
