package com.example.bolgsystem_lab11.Repository;

import com.example.bolgsystem_lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Using find
    User findUserByUserId(Integer userId);

    User findUserByUsername(String username);

    // Using JPQL
    @Query("select u.registrationDate from User u where u.userId = ?1")
    LocalDateTime findRegistrationDateByUserId(Integer userId);
}