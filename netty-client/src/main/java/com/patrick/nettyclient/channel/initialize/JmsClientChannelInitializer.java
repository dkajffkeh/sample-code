package com.patrick.nettyclient.channel.initialize;

import com.patrick.tcpprotocol.decode.ByteToPacketDecoder;
import com.patrick.tcpprotocol.encode.SampleStringToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.jetbrains.annotations.NotNull;

public class JmsClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(@NotNull SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // decode
        pipeline.addLast(new ByteToPacketDecoder());

        // encode
        pipeline.addLast(new SampleStringToByteEncoder());
    }
}
