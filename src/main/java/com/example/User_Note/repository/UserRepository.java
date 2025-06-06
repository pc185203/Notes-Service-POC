package com.example.User_Note.repository;

import com.example.User_Note.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    //@NativeQuery(value = "select * from users where binary username = ?1")      // by default comparison is case insensitive in mysql
    //ALTER TABLE User_notes.users MODIFY username VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs; - to make it sensitive
    Optional<Users> findByUsername(String username);    // optional to avoid null pointer exception
}
