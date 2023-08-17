package com.patrick.tcpprotocol.decode;

import com.patrick.tcpprotocol.model.RequestData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RequestDecoder extends MessageToMessageDecoder<RequestData> {

    private static final Logger LOG = LoggerFactory.getLogger(RequestDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, RequestData msg, List<Object> out) {
        LOG.info("message => {}", msg);
        out.add(msg);
    }
}
