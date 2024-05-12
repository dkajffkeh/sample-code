package com.patrick.nettyclient.channel;

import com.patrick.nettyclient.channel.initialize.JmsClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

@Component
public class JmsSampleReportClient {

    public ChannelFuture connect() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new JmsClientChannelInitializer());

        return b.connect("127.0.0.1", 32003);
    }
}
