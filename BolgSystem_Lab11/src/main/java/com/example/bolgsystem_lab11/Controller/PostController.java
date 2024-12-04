package com.example.bolgsystem_lab11.Controller;

import com.example.bolgsystem_lab11.ApiResponse.ApiResponse;
import com.example.bolgsystem_lab11.Model.Post;
import com.example.bolgsystem_lab11.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    // 1. Declare a dependency for CategoryService using Dependency Injection
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 2. CRUD
    // 2.1 Get
    @GetMapping("/get")
    public ResponseEntity getAllPosts() {
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    // 2.2 Post
    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("New Post Added."));
    }

    // 2.3 Update
    @PutMapping("/update/{postId}")
    public ResponseEntity updatePost(@PathVariable Integer postId, @RequestBody @Valid Post post, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.updatePost(postId, post);
        return ResponseEntity.status(200).body(new ApiResponse("Post Updated."));
    }

    // 2.4 Delete
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(200).body(new ApiResponse("Post Deleted."));
    }

    // 3. Extra endpoints
    // 3.1 Endpoint to get all posts by user_id
    @GetMapping("/getAllUserPosts/{userId}")
    public ResponseEntity getAllPostsByUserId(@PathVariable Integer userId) {
        List<Post> userPosts = postService.getAllPostsByUserId(userId);
        return ResponseEntity.status(200).body(userPosts);
    }

    // 3.2 Endpoint to get post by title
    @GetMapping("/getPostByTitle/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title) {
        Post post = postService.getPostByTitle(title);
        return ResponseEntity.status(200).body(post);
    }

    // 3.3 Endpoint to get all posts before a certain date by a date
    @GetMapping("/getAllPostsBeforeDate/{date}")
    public ResponseEntity getAllPostsBeforeDate(@PathVariable LocalDateTime date) {
        List<Post> posts = postService.getAllPostsBeforeDate(date);
        return ResponseEntity.status(200).body(posts);
    }
}