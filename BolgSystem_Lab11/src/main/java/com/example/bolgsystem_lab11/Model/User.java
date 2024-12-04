package com.example.bolgsystem_lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Entity
@Table(name = "system-user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty(message = "Username can't be empty.")
    @Size(min = 5, max = 10, message = "Username length must be between 5-10 characters.")
    @Column(columnDefinition = "varchar(10) not null unique")
    @Check(constraints = "length(username) >= 5")
    private String username;

    @NotEmpty(message = "Password can't be empty.")
    @Size(min = 5, max = 20, message = "Password length must be between 5-20 characters.")
    @Column(columnDefinition = "varchar(20) not null")
    @Check(constraints = "length(password) >= 5")
    private String password;

    @Email(message = "Invalid email format.")
    @NotEmpty(message = "Email can't be empty.")
    @Column(columnDefinition = "varchar(255) not null unique")
    @Check(constraints = "email LIKE '_%@_%._%'")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime registrationDate;

    // Getters & setters
    public Integer getUserId() {
        return userId;
    }

    public @NotEmpty(message = "Username can't be empty.") @Size(min = 5, max = 10, message = "Username length must be between 5-10 characters.") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "Username can't be empty.") @Size(min = 5, max = 10, message = "Username length must be between 5-10 characters.") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "Password can't be empty.") @Size(min = 5, max = 20, message = "Password length must be between 5-20 characters.") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password can't be empty.") @Size(min = 5, max = 20, message = "Password length must be between 5-20 characters.") String password) {
        this.password = password;
    }

    public @Email(message = "Invalid email format.") @NotEmpty(message = "Email can't be empty.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email format.") @NotEmpty(message = "Email can't be empty.") String email) {
        this.email = email;
    }
}