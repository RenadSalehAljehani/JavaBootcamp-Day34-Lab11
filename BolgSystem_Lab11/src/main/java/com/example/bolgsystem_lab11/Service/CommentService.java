package com.example.bolgsystem_lab11.Service;

import com.example.bolgsystem_lab11.ApiResponse.ApiException;
import com.example.bolgsystem_lab11.Model.Comment;
import com.example.bolgsystem_lab11.Model.Post;
import com.example.bolgsystem_lab11.Repository.CommentRepository;
import com.example.bolgsystem_lab11.Repository.PostRepository;
import com.example.bolgsystem_lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    // 1. Declare a dependency for CommentRepository using Dependency Injection
    private final CommentRepository commentRepository;

    // 2. Declare a dependency for UserRepository using Dependency Injection
    private final UserRepository userRepository;

    // 3. Declare a dependency for PostRepository using Dependency Injection
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // 4. CRUD
    // 4.1 Get
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // 4.2 Post
    public void addComment(Comment comment) {
        // Check the existence of user id and post id for the comment
        if (userRepository.findUserByUserId(comment.getUserId()) == null
                && postRepository.findPostByPostId(comment.getPostId()) == null) {
            throw new ApiException("User Id and Post Id Not Found.");
        }
        if (userRepository.findUserByUserId(comment.getUserId()) == null) {
            throw new ApiException("User Id Not Found.");
        }
        if (postRepository.findPostByPostId(comment.getPostId()) == null) {
            throw new ApiException("Post Id Not Found.");
        }
        commentRepository.save(comment);
    }

    // 4.3 Update
    public void updateComment(Integer commentId, Comment comment) {
        // 1. check if the comment to be updated exists
        Comment oldComment = commentRepository.findCommentByCommentId(commentId);
        if (oldComment == null) {
            throw new ApiException("Comment Not Found.");
        }

        // 2. Check the existence of user id and post id for the new comment
        if (userRepository.findUserByUserId(comment.getUserId()) == null
                && postRepository.findPostByPostId(comment.getPostId()) == null) {
            throw new ApiException("User Id and Post Id Not Found.");
        }
        if (userRepository.findUserByUserId(comment.getUserId()) == null) {
            throw new ApiException("User Id Not Found.");
        }
        if (postRepository.findPostByPostId(comment.getPostId()) == null) {
            throw new ApiException("Post Id Not Found.");
        }

        // 3. Update the old comment if all checks are passed
        oldComment.setUserId(comment.getUserId());
        oldComment.setPostId(comment.getPostId());
        oldComment.setContent(comment.getContent());
        commentRepository.save(oldComment);
    }

    // 4.4 Delete
    public void deleteComment(Integer commentId) {
        Comment oldComment = commentRepository.findCommentByCommentId(commentId);
        if (oldComment == null) {
            throw new ApiException("Comment Not Found.");
        }
        commentRepository.delete(oldComment);
    }

    // 5. Extra endpoints
    // 5.1 Endpoint to get all comments for one post by post_id
    public List<Comment> getAllCommentsByPostId(Integer postId) {
        // Check if post exists
        if (postRepository.findPostByPostId(postId) == null) {
            throw new ApiException("Post Id Not Found.");
        }
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);
        if (comments.isEmpty()) {
            throw new ApiException("No Comments For Post with Id (" + postId + ") Has Been Found.");
        }
        return comments;
    }

    // 5.2 Endpoint to get all comments for a specific user
    public List<Comment> getAllCommentsForAUser(Integer userId) {
        // Check if user exists
        if (userRepository.findUserByUserId(userId) == null) {
            throw new ApiException("User Id Not Found.");
        }
        List<Comment> userComments = commentRepository.findCommentsByUserId(userId);
        if (userComments.isEmpty()) {
            throw new ApiException("No Comments For User with Id (" + userId + ") Has Been Found.");
        }
        return userComments;
    }
}