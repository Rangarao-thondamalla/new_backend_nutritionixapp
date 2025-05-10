package com.example.contactMessage.service;

import com.example.contactMessage.Entities.ContactMessage;
import com.example.contactMessage.repository.ContactMessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.contactMessage.config.RabbitMQConfig.QUEUE_NAME;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository repository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Send message to RabbitMQ
    public void sendMessageToQueue(ContactMessage message) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
    }

    // Save message in the database
    public ContactMessage saveMessage(ContactMessage message) {
        return repository.save(message);
    }

    // Get all messages
    public List<ContactMessage> getAllMessages() {
        return repository.findAll();
    }

    // Get a message by ID
    public Optional<ContactMessage> getMessageById(int id) {
        return repository.findById(id);
    }

    // Delete a message by ID
    public void deleteMessage(int id) {
        repository.deleteById(id);
    }
}
