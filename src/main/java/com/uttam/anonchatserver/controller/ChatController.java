package com.uttam.anonchatserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.uttam.anonchatserver.models.Message;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message") // app/message topic for user to send
    @SendTo("/chatroom/public") // to receive a message listen to this
    public Message receiveMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message") //private message to user
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message); //dynamic topics creation for sending it to user    // /user/Uttam/private
        System.out.println(message.toString());
        return message;
    }
}