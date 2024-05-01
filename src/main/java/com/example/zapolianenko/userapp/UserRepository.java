package com.example.zapolianenko.userapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.birthDate BETWEEN :from AND :to")
    List<User> findAllByBirthDateBetween(LocalDate from, LocalDate to);
    boolean existsByEmail(String email);

}
