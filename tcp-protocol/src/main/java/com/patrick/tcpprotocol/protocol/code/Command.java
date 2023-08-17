package com.patrick.tcpprotocol.protocol.code;

public enum Command {

    SAMPLE_REQ(1010)
    ;

    private final int code;


    Command(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
