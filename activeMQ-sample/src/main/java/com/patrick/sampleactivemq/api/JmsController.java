package com.patrick.sampleactivemq.api;

import com.patrick.sampleactivemq.api.param.JmsInsertRequestParam;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class JmsController {

    private final JmsMessagingTemplate jmsMessagingTemplate;

    public JmsController(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @PostMapping
    public void insertJmsMessage(@RequestBody JmsInsertRequestParam jmsInsertRequestParam) {
        jmsMessagingTemplate.convertAndSend(jmsInsertRequestParam.destinationName(), jmsInsertRequestParam.message());
    }

}
