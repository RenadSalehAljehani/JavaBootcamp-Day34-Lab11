package com.example.bolgsystem_lab11.Repository;

import com.example.bolgsystem_lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Using find
    Post findPostByPostId(Integer postId);

    List<Post> findAllPostsByUserId(Integer userId);

    Post findPostByTitle(String title);

    // Using JPQL
    @Query("select p from Post p where p.categoryId=?1")
    Post checkCategoryId(Integer categoryId);

    @Query("select p from Post p where p.userId=?1")
    Post checkUserId(Integer userId);

    @Query("select p from Post p where p.publishDate < ?1")
    List<Post> findAllPostsBeforeDate(LocalDateTime date);
}