package com.patrick.nettyclient.request.encode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.tcpprotocol.protocol.Packet;
import com.patrick.tcpprotocol.protocol.code.Command;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import java.nio.CharBuffer;

public class RequestDataEncoder
        extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          Packet msg, ByteBuf out) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Command command = msg.getCommand();
        String jsonString = objectMapper.writeValueAsString(msg.getOptions());
        ByteBuf options = ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(jsonString), CharsetUtil.UTF_8);
        out.writeInt(command.getCode());
        out.writeBytes(options);
        options.release();

    }
}
