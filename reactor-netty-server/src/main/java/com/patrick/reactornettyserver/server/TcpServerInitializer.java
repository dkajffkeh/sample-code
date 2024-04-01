package com.patrick.reactornettyserver.server;

import com.patrick.reactornettyserver.handler.StringLogger;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.resources.LoopResources;
import reactor.netty.tcp.TcpServer;

@Component
public class TcpServerInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(TcpServerInitializer.class);

    public void startServer() {

        LOG.info("Starting Report Tcp Server.");

        LoopResources loopResources = LoopResources.create("event-loop", 1, 4, true);

        ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

        TcpServer tcpServer = TcpServer.create()
                .port(9992)
                .wiretap(true)
                .runOn(loopResources)
                .doOnConnection(connection -> {
                    connection.addHandlerLast(new StringLogger());
                    connection.addHandlerLast(new ChannelHandlerAdapter() {
                        @Override
                        public void handlerAdded(ChannelHandlerContext ctx) {
                            Channel channel = ctx.channel();
                            channelGroup.add(channel);
                            LOG.info("channel added: {}", channel);
                        }

                        @Override
                        public void handlerRemoved(ChannelHandlerContext ctx) {
                            LOG.info(ctx.channel().id().toString());
                            LOG.info("channel removed: {}", ctx.channel());
                        }
                    });
                })
                .handle((nettyInbound, nettyOutbound) -> nettyInbound.receiveObject()
                        .ofType(String.class)
                        .flatMap(value -> nettyOutbound.sendString(Mono.justOrEmpty(value))));

        tcpServer = tcpServer
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

        tcpServer.bind().block();
    }
}
