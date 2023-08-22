package com.patrick.tcpprotocol.protocol;

import java.io.Serializable;

public class SampleRequestPacket {

    private String name;

    private String phoneNumber;

    public SampleRequestPacket() {
    }

    public SampleRequestPacket(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "SampleRequestPacket{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
