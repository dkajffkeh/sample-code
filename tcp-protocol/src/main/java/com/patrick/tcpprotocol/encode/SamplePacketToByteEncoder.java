package com.patrick.tcpprotocol.encode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.tcpprotocol.protocol.Packet;
import com.patrick.tcpprotocol.protocol.SamplePacket;
import com.patrick.tcpprotocol.protocol.code.Command;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import java.nio.CharBuffer;

public class SamplePacketToByteEncoder extends MessageToByteEncoder<Packet> {

    private final ObjectMapper mapper;

    public SamplePacketToByteEncoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        Command command = msg.getCommand();
        String jsonText = this.mapper.writeValueAsString(msg.getOptions());
        ByteBuf options = ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(jsonText), CharsetUtil.UTF_8);
        out.writeBytes(SamplePacket.STX.copy());
        out.writeInt(command.getCode());
        out.writeBytes(options);
        out.writeBytes(SamplePacket.ETX.copy());
        options.release();
    }
}
