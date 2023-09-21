package com.patrick.nettyclient.channel;

import com.patrick.nettyclient.request.encode.RequestDataEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class SampleChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline().addLast(
                new RequestDataEncoder()
        );
    }
}
