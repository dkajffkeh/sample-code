package com.patrick.tcpprotocol.decode;

import com.patrick.tcpprotocol.protocol.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestDecoder extends MessageToMessageDecoder<Packet> {

    private static final Logger LOG = LoggerFactory.getLogger(RequestDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet msg, List<Object> out) {
        LOG.info("message => {}", msg);
        out.add(msg);
    }
}
