package com.patrick.tcpprotocol.protocol;

import com.patrick.tcpprotocol.protocol.code.Command;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class SamplePacket<T> implements Packet {

    public static final ByteBuf STX = Unpooled.wrappedBuffer(new byte[] { 0x24, 0x02, 0x24 });
    public static final ByteBuf ETX = Unpooled.wrappedBuffer(new byte[] { 0x24, 0x03, 0x24 });
    public static final ByteBuf ACK = Unpooled.wrappedBuffer(new byte[] { 0x24, 0x06, 0x24 });
    public static final ByteBuf NAK = Unpooled.wrappedBuffer(new byte[] { 0x24, 0x15, 0x24 });

    private Command command;

    // 명령 옵션
    private T options;

    public SamplePacket(Command command, T options) {
        this.command = command;
        this.options = options;
    }

    @Override
    public Command getCommand() {
        return this.command;
    }

    @Override
    public Object getOptions() {
        return this.options;
    }
}
