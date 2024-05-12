package com.patrick.sampleactivemq.api.param;

import com.patrick.tcpprotocol.message.JmsHelloMessage;
import java.io.Serializable;

public record JmsInsertRequestParam(String destinationName, JmsHelloMessage message) implements
        Serializable {
}
