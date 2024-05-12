package com.patrick.nettyclient.channel.initialize;

import com.patrick.tcpprotocol.decode.ByteToPacketDecoder;
import com.patrick.tcpprotocol.encode.SampleStringToByteEncoder;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

public class JmsClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(@NotNull SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // decode
        pipeline.addLast(new ByteToPacketDecoder());

        // encode
        pipeline.addLast(new SampleStringToByteEncoder());

        // heartBeat trigger
        pipeline.addLast(new IdleStateHandler(0, 3 * 1000, 0, TimeUnit.MILLISECONDS));
        pipeline.addLast(new ChannelInboundHandlerAdapter());
    }
}
