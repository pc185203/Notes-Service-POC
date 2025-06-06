package com.example.User_Note.repository;

import com.example.User_Note.model.UserNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNoteRepository extends JpaRepository<UserNotes,Integer> {
}
