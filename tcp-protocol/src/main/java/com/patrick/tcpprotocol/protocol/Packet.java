package com.patrick.tcpprotocol.protocol;

import com.patrick.tcpprotocol.protocol.code.Command;

public interface Packet {

    Command getCommand();

    Object getOptions();

}
