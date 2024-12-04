package com.example.bolgsystem_lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @NotNull(message = "Category id can't be empty.")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;

    @NotEmpty(message = "Title can't be empty.")
    @Size(min = 5, max = 20, message = "Title length must be between 5-20 characters.")
    @Column(columnDefinition = "varchar(20) not null unique")
    @Check(constraints = "length(title) >= 5")
    private String title;

    @NotEmpty(message = "Content can't be empty.")
    @Size(min = 5, max = 500, message = "Content length must be between 5-500 characters.")
    @Column(columnDefinition = "varchar(500) not null")
    @Check(constraints = "length(content) >= 5")
    private String content;

    @NotNull(message = "User id can't be empty.")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime publishDate;

    // Setters & getters
    public Integer getPostId() {
        return postId;
    }

    public @NotNull(message = "Category id can't be empty.") Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull(message = "Category id can't be empty.") Integer categoryId) {
        this.categoryId = categoryId;
    }

    public @NotEmpty(message = "Title can't be empty.") @Size(min = 5, max = 20, message = "Title length must be between 5-20 characters.") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Title can't be empty.") @Size(min = 5, max = 20, message = "Title length must be between 5-20 characters.") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "Content can't be empty.") @Size(min = 5, max = 500, message = "Content length must be between 5-500 characters.") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "Content can't be empty.") @Size(min = 5, max = 500, message = "Content length must be between 5-500 characters.") String content) {
        this.content = content;
    }

    public @NotNull(message = "User id can't be empty.") Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User id can't be empty.") Integer userId) {
        this.userId = userId;
    }
}