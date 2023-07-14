package com.patrick.tcpprotocol.decode;

import com.patrick.tcpprotocol.model.RequestData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class RequestDecoder extends MessageToMessageDecoder<RequestData> {

    @Override
    protected void decode(ChannelHandlerContext ctx, RequestData msg, List<Object> out) {
        System.out.println(msg);
        out.add(msg);
    }
}
