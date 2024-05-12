package com.patrick.tcpprotocol.encode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.tcpprotocol.protocol.SamplePacket;
import com.patrick.tcpprotocol.protocol.code.Command;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import java.nio.CharBuffer;

public class SampleStringToByteEncoder extends MessageToByteEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        out.writeBytes(SamplePacket.STX.copy());
        out.writeBytes(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(msg), CharsetUtil.UTF_8));
        out.writeBytes(SamplePacket.ETX.copy());
    }
}
