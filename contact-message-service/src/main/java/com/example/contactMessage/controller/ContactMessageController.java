package com.example.contactMessage.controller;

import com.example.contactMessage.Entities.ContactMessage;
import com.example.contactMessage.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contact")
public class ContactMessageController {

    @Autowired
    private ContactMessageService service;

    // Send message to RabbitMQ
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody ContactMessage message) {
        service.sendMessageToQueue(message);
        return ResponseEntity.ok("Message sent to RabbitMQ successfully");
    }

    // Get all messages
    @GetMapping("/messages")
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        return ResponseEntity.ok(service.getAllMessages());
    }

    // Get a message by ID
    @GetMapping("/{id}")
    public ResponseEntity<ContactMessage> getMessageById(@PathVariable int id) {
        Optional<ContactMessage> message = service.getMessageById(id);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a message by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
        service.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
