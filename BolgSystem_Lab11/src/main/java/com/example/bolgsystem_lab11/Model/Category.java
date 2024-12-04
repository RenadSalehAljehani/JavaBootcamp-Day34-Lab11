package com.example.bolgsystem_lab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @NotEmpty(message = "Name can't be empty.")
    @Size(min = 5, max = 15, message = "Name length must be between 5-15 characters.")
    @Column(columnDefinition = "varchar(15) not null unique")
    @Check(constraints = "length(name) >= 5")
    private String name;

    // Getters & setters
    public Integer getCategoryId() {
        return categoryId;
    }

    public @NotEmpty(message = "Name can't be empty.") @Size(min = 5, max = 15, message = "Name length must be between 5-15 characters.") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name can't be empty.") @Size(min = 5, max = 15, message = "Name length must be between 5-15 characters.") String name) {
        this.name = name;
    }
}