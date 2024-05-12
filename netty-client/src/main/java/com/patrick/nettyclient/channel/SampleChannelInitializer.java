package com.patrick.nettyclient.channel;

import com.patrick.tcpprotocol.encode.SampleStringToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class SampleChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline cp = ch.pipeline();

        // encode
        cp.addLast(new SampleStringToByteEncoder());

        // decode

    }
}
