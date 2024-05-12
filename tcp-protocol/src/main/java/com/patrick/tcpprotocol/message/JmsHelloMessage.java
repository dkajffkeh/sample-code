package com.patrick.tcpprotocol.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
public record JmsHelloMessage(String name, String phoneNumber) implements Serializable {

}
