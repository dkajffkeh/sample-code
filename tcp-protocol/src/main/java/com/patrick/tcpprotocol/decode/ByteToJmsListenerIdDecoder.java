package com.patrick.tcpprotocol.decode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.tcpprotocol.protocol.SampleRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteToJmsListenerIdDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final Charset charset = StandardCharsets.UTF_8;

    private static final Logger LOGGER = LoggerFactory.getLogger(ByteToJmsListenerIdDecoder.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf msg, List<Object> out) throws JsonProcessingException {
        int totalBytes = msg.readableBytes();
        msg.readSlice(3);
        ByteBuf options = msg.readBytes(totalBytes - 6);
        msg.readSlice(3);
        String clientId = options.toString(charset);
        LOGGER.info(clientId);
        out.add(clientId);
    }
}
