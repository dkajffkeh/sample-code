package com.patrick.reactornettyserver.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringLogger extends MessageToMessageDecoder<ByteBuf> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringLogger.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        LOGGER.info(msg.toString(CharsetUtil.UTF_8));
        out.add(msg.toString(CharsetUtil.UTF_8));
    }
}
