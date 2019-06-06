package com.vng.chatservice.config;

import com.vng.chatservice.controller.PlainWebSocket;
import com.vng.chatservice.support.HttpHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
@EnableWebSocket
public class WebSocketConfigWithoutStomp implements WebSocketConfigurer {

    @Autowired
    private HttpHandshakeInterceptor handshakeInterceptor;

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxBinaryMessageBufferSize(1024000000);
        return container;
    }
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new PlainWebSocket(), "/binary").setAllowedOrigins("*").addInterceptors(handshakeInterceptor)
                ;
    }

}
