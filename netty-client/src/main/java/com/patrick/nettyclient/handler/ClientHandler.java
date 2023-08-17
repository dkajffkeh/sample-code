package com.patrick.nettyclient.handler;

import com.patrick.nettyclient.model.RequestData;
import com.patrick.tcpprotocol.protocol.SamplePacket;
import com.patrick.tcpprotocol.protocol.code.Command;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        RequestData msg = new RequestData();
        msg.setIntValue(1);
        msg.setStringValue("Hello World");

        ctx.writeAndFlush(new SamplePacket<>(Command.SAMPLE_REQ, msg));
    }

}
