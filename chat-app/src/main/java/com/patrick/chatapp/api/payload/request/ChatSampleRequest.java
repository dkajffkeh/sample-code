package com.patrick.chatapp.api.payload.request;

public record ChatSampleRequest(
        String from,
        String to,
        String message
) {

}
