package com.patrick.nettyclient.channel;

import com.patrick.nettyclient.channel.initialize.JmsClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ChannelConnectScheduler {

    private ChannelFuture channelFuture;

    private EventLoopGroup group;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelConnectScheduler.class);

    @Scheduled(initialDelay = 1000, fixedDelay = 10 * 1000)
    void connectJmsServer() throws InterruptedException {
        if(this.channelFuture == null) {
            LOGGER.info("Connect to Jms Server");
            this.channelFuture = this.connect();

            if(this.channelFuture.isSuccess()) {
                channelFuture.channel().writeAndFlush("yhy1011").sync();
            }
        }

        if(!this.channelFuture.channel().isActive()) {
            group.shutdownGracefully();
            this.channelFuture = null;
        }

    }

    private ChannelFuture connect() throws InterruptedException {
        group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new JmsClientChannelInitializer());

        return b.connect("127.0.0.1", 32003).await();
    }

}
