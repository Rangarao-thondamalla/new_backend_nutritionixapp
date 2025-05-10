package com.example.contactMessage.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @RabbitListener(queues = "contactQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
