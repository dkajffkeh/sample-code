package com.patrick.tcpprotocol.handler.decode;

import com.patrick.tcpprotocol.protocol.SamplePacket;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class SamplePacketDelimiterFrameDecoder extends DelimiterBasedFrameDecoder {

    public SamplePacketDelimiterFrameDecoder(int maxFrameLength) {
        super(maxFrameLength, SamplePacket.ETX.copy(), SamplePacket.ACK.copy(), SamplePacket.NAK.copy());
    }
}
