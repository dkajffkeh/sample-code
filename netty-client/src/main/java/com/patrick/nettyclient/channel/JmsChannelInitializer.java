package com.patrick.nettyclient.channel;

import com.patrick.tcpprotocol.decode.ByteToJmsListenerIdDecoder;
import com.patrick.tcpprotocol.decode.ByteToPacketDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;

public class JmsChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(@NotNull SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // decode
        pipeline.addLast(new ByteToPacketDecoder());

        // incode

    }
}
