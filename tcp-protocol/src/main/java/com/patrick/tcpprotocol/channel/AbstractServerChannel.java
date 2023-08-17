package com.patrick.tcpprotocol.channel;

import com.patrick.tcpprotocol.handler.decode.ByteToRequestDecoder;
import com.patrick.tcpprotocol.handler.encoder.ResponseDataEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractServerChannel extends ChannelInitializer<Channel>
        implements InitializingBean, DisposableBean {

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    @Override
    protected void initChannel(Channel ch) {
        ch.pipeline().addLast(
                /*new SamplePacketDelimiterFrameDecoder(1048576),*/
                new ByteToRequestDecoder(),
                /*new RequestDecoder(),*/
                new ResponseDataEncoder());
    }

    @Override
    public void destroy() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }

        bossGroup = null;
        workerGroup = null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap;

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(this);

        serverBootstrap.bind(9991).sync();
    }




}
