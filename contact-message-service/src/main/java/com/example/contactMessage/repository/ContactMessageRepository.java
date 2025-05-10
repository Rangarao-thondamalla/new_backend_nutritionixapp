package com.example.contactMessage.repository;

import com.example.contactMessage.Entities.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Integer> {
    // JpaRepository provides built-in CRUD methods
}
