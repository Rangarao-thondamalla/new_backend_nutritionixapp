package com.example.contactMessage.consumer;

import com.example.contactMessage.Entities.ContactMessage;
import com.example.contactMessage.repository.ContactMessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.contactMessage.config.RabbitMQConfig.QUEUE_NAME;

@Service
public class ContactMessageConsumer {

    @Autowired
    private ContactMessageRepository repository;

    @RabbitListener(queues = QUEUE_NAME)
    public void consumeMessage(ContactMessage message) {
        System.out.println("Received message from RabbitMQ: " + message);
        repository.save(message);
    }
}
