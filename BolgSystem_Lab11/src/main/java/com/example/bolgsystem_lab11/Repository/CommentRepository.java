package com.example.bolgsystem_lab11.Repository;

import com.example.bolgsystem_lab11.Model.Comment;
import com.example.bolgsystem_lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // Using find
    Comment findCommentByCommentId(Integer commentId);

    List<Comment> findCommentsByPostId(Integer postId);

    List<Comment> findCommentsByUserId(Integer userId);

    // Using JPQL
    @Query("select c from Comment c where c.userId=?1")
    Comment checkUserId(Integer userId);

    @Query("select c from Comment c where c.postId=?1")
    Comment checkPostId(Integer postId);
}