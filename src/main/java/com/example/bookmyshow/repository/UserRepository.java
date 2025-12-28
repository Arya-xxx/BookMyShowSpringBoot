package com.example.bookmyshow.repository;

import com.example.bookmyshow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.*;
import java.util.List;

/*
JpaRepository<T, ID> is a generic interface where:

T → Entity type

ID → Primary key type
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    // JpaRepository already gives:
    // save(), findById(), findAll(), deleteById(), etc.

    // Custom query method:
    // Spring will auto-generate query: SELECT count(*) FROM users WHERE email = ?
    List<User> findAll();

    boolean existsByEmail(String email);
}
