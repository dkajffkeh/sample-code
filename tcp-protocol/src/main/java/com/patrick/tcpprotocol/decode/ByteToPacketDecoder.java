package com.patrick.tcpprotocol.decode;

import static com.patrick.tcpprotocol.protocol.code.Command.fromValue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.tcpprotocol.message.JmsHelloMessage;
import com.patrick.tcpprotocol.protocol.SamplePacket;
import com.patrick.tcpprotocol.protocol.code.Command;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteToPacketDecoder extends MessageToMessageDecoder<ByteBuf> {

    private final Charset charset = StandardCharsets.UTF_8;

    private static final Logger LOGGER = LoggerFactory.getLogger(ByteToPacketDecoder.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf msg, List<Object> out) throws JsonProcessingException {
        int length = msg.readableBytes();
        msg.readSlice(3);
        int command = msg.readInt();
        ByteBuf options = msg.readSlice(length - 10);
        String jsonText = options.toString(charset);
        JmsHelloMessage jmsHelloMessage = OBJECT_MAPPER.readValue(jsonText, JmsHelloMessage.class);
        LOGGER.info(jmsHelloMessage.toString());
        msg.readSlice(3);
        out.add(new SamplePacket<>(fromValue(command), jmsHelloMessage));
    }
}
