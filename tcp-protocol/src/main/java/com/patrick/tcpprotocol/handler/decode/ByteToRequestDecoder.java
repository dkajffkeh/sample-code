package com.patrick.tcpprotocol.handler.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteToRequestDecoder extends MessageToMessageDecoder<ByteBuf> {

    private static final Logger LOG = LoggerFactory.getLogger(ByteToRequestDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf msg, List<Object> out) {
        int length = msg.readableBytes();

        LOG.info(length+"");

        LOG.info(msg.readInt()+"");



 /*       RequestData data = new RequestData();
        data.setIntValue(msg.readInt());
        int strLen = msg.readInt();
        data.setStringValue(msg.readSlice(5).toString(CharsetUtil.UTF_8));*/
        out.add(0);
    }
}
