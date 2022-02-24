package com.uttam.anonchatserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //starting part for all websocket connection
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); //setAll- allow cross origin , for all origin
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");  //to send message   /app/message
        registry.enableSimpleBroker("/chatroom","/user");  //topics prefixes
        registry.setUserDestinationPrefix("/user");
    }
}