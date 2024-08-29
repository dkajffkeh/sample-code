package com.patrick.chatapp.api.payload.request;

public class ChatSampleRequest {

    private String from;
    private String to;
    private String message;

    public ChatSampleRequest() {

    }

    public ChatSampleRequest(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }
}
