package com.example.bolgsystem_lab11.Controller;

import com.example.bolgsystem_lab11.ApiResponse.ApiResponse;
import com.example.bolgsystem_lab11.Model.Comment;
import com.example.bolgsystem_lab11.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    // 1. Declare a dependency for CommentService using Dependency Injection
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 2. CRUD
    // 2.1 Get
    @GetMapping("/get")
    public ResponseEntity getAllComments() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    // 2.2 Post
    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("New Comment Added."));
    }

    // 2.3 Update
    @PutMapping("/update/{commentId}")
    public ResponseEntity updateComment(@PathVariable Integer commentId, @RequestBody @Valid Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(commentId, comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment Updated."));
    }

    // 2.4 Delete
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(200).body(new ApiResponse("Comment Deleted."));
    }

    // 3. Extra endpoints
    // 3.1 Endpoint to get all comments for one post by post_id
    @GetMapping("/getAllCommentsByPostId/{postId}")
    public ResponseEntity getAllCommentsByPostId(@PathVariable Integer postId) {
        List<Comment> comments = commentService.getAllCommentsByPostId(postId);
        return ResponseEntity.status(200).body(comments);
    }

    // 3.2 Endpoint to get all comments for a specific user
    @GetMapping("/getAllCommentsForAUser/{userId}")
    public ResponseEntity getAllCommentsForAUser(@PathVariable Integer userId) {
        List<Comment> userComments = commentService.getAllCommentsForAUser(userId);
        return ResponseEntity.status(200).body(userComments);
    }
}