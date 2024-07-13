package com.patrick.dockerapp.verify.activemq;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

// @Component
public class ActiveMQConnectionVerifier {

    private final JmsMessagingTemplate jmsMessagingTemplate;


    private final Logger LOGGER = LoggerFactory.getLogger(ActiveMQConnectionVerifier.class);

    public ActiveMQConnectionVerifier(
            JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @PostConstruct
    public void verifyActiveMQConnection() {
        try {
            LOGGER.info("Sending a ping message to ActiveMQ");
            jmsMessagingTemplate.convertAndSend("ping", "ping");
        } catch (Exception e) {
            // 예외 발생 시 애플리케이션 종료
            throw new RuntimeException("Failed to connect to ActiveMQ", e);
        }
    }
}
