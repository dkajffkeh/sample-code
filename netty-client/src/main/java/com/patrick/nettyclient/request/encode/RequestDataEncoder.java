package com.patrick.nettyclient.request.encode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.nettyclient.model.RequestData;
import com.patrick.tcpprotocol.protocol.Packet;
import com.patrick.tcpprotocol.protocol.SamplePacket;
import com.patrick.tcpprotocol.protocol.SampleRequestPacket;
import com.patrick.tcpprotocol.protocol.code.Command;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

public class RequestDataEncoder
        extends MessageToByteEncoder<SampleRequestPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx,
            SampleRequestPacket msg, ByteBuf out) throws JsonProcessingException {
        out.writeBytes(SamplePacket.STX.copy());
        ByteBuf options = ByteBufUtil
                .encodeString(
                        ctx.alloc(),
                        CharBuffer.wrap(new ObjectMapper().writeValueAsString(msg)),
                        StandardCharsets.UTF_8);
        out.writeBytes(options);
        out.writeBytes(SamplePacket.ETX.copy());
    }

}
