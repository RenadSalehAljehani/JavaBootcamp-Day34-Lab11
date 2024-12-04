package com.example.bolgsystem_lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @NotNull(message = "User id can't be empty.")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Post id can't be empty.")
    @Column(columnDefinition = "int not null")
    private Integer postId;

    @NotEmpty(message = "Content can't be empty.")
    @Size(min = 5, max = 500, message = "Content length must be between 5-500 characters.")
    @Column(columnDefinition = "varchar(500) not null")
    @Check(constraints = "length(content) >= 5")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime commentDate;

    // Setters & getters
    public Integer getCommentId() {
        return commentId;
    }


    public @NotNull(message = "User id can't be empty.") Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull(message = "User id can't be empty.") Integer userId) {
        this.userId = userId;
    }

    public @NotNull(message = "Post id can't be empty.") Integer getPostId() {
        return postId;
    }

    public void setPostId(@NotNull(message = "Post id can't be empty.") Integer postId) {
        this.postId = postId;
    }

    public @NotEmpty(message = "Content can't be empty.") @Size(min = 5, max = 500, message = "Content length must be between 5-500 characters.") String getContent() {
        return content;
    }

    public void setContent(@NotEmpty(message = "Content can't be empty.") @Size(min = 5, max = 500, message = "Content length must be between 5-500 characters.") String content) {
        this.content = content;
    }
}