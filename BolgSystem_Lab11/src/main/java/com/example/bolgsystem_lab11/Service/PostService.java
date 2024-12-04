package com.example.bolgsystem_lab11.Service;

import com.example.bolgsystem_lab11.ApiResponse.ApiException;
import com.example.bolgsystem_lab11.Model.Post;
import com.example.bolgsystem_lab11.Repository.CategoryRepository;
import com.example.bolgsystem_lab11.Repository.PostRepository;
import com.example.bolgsystem_lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    // 1. Declare a dependency for PostRepository using Dependency Injection
    private final PostRepository postRepository;

    // 2. Declare a dependency for UserRepository using Dependency Injection
    private final UserRepository userRepository;

    // 3. Declare a dependency for CategoryRepository using Dependency Injection
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    // 4. CRUD
    // 4.1 Get
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 4.2 Post
    public void addPost(Post post) {
        // Check the existence of category id and user id for the post
        if (categoryRepository.findCategoryByCategoryId(post.getCategoryId()) == null
                && userRepository.findUserByUserId(post.getUserId()) == null) {
            throw new ApiException("Category Id and User Id Not Found.");
        }
        if (categoryRepository.findCategoryByCategoryId(post.getCategoryId()) == null) {
            throw new ApiException("Category Not Found.");
        }
        if (userRepository.findUserByUserId(post.getUserId()) == null) {
            throw new ApiException("User Id Not Found.");
        }
        postRepository.save(post);
    }

    // 4.3 Update
    public void updatePost(Integer postId, Post post) {
        // 1. check if the post to be updated exists
        Post oldPost = postRepository.findPostByPostId(postId);
        if (oldPost == null) {
            throw new ApiException("Post Not Found.");
        }

        // 2. Check the existence of category id and user id for the new post
        if (categoryRepository.findCategoryByCategoryId(post.getCategoryId()) == null
                && userRepository.findUserByUserId(post.getUserId()) == null) {
            throw new ApiException("Category Id and User Id Not Found.");
        }
        if (categoryRepository.findCategoryByCategoryId(post.getCategoryId()) == null) {
            throw new ApiException("Category Not Found.");
        }
        if (userRepository.findUserByUserId(post.getUserId()) == null) {
            throw new ApiException("User Id Not Found.");
        }

        // 3. Update the old post if all checks are passed
        oldPost.setCategoryId(post.getCategoryId());
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setUserId(post.getUserId());
        postRepository.save(oldPost);
    }

    // 4.4 Delete
    public void deletePost(Integer postId) {
        Post oldPost = postRepository.findPostByPostId(postId);
        if (oldPost == null) {
            throw new ApiException("Post Not Found.");
        }
        postRepository.delete(oldPost);
    }

    // 5. Extra endpoints
    // 5.1 Endpoint to get all posts by user_id
    public List<Post> getAllPostsByUserId(Integer userId) {
        // Check if user exists
        if (userRepository.findUserByUserId(userId) == null) {
            throw new ApiException("User Id Not Found.");
        }
        List<Post> posts = postRepository.findAllPostsByUserId(userId);
        if (posts.isEmpty()) {
            throw new ApiException("No Posts For User with Id (" + userId + ") Has Been Found.");
        }
        return posts;
    }

    // 5.2  Endpoint to get post by title
    public Post getPostByTitle(String title) {
        Post post = postRepository.findPostByTitle(title);
        if (post == null) {
            throw new ApiException("No Post With Title (" + title + ") Has Been Found.");
        }
        return post;
    }

    // 5.3 Endpoint to get all posts before a certain date by a date
    public List<Post> getAllPostsBeforeDate(LocalDateTime date) {
        return postRepository.findAllPostsBeforeDate(date);
    }
}