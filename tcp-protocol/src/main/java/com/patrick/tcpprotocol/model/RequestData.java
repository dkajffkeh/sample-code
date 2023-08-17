package com.patrick.tcpprotocol.model;

public class RequestData {
    private int intValue;
    private String stringValue;

    public RequestData(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "intValue=" + intValue +
                ", stringValue='" + stringValue + '\'' +
                '}';
    }
}
