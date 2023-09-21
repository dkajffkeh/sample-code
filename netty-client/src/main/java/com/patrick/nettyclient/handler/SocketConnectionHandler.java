package com.patrick.nettyclient.handler;

import com.patrick.nettyclient.channel.SampleChannelInitializer;
import com.patrick.tcpprotocol.protocol.SamplePacket;
import com.patrick.tcpprotocol.protocol.SampleRequestPacket;
import com.patrick.tcpprotocol.protocol.code.Command;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Service;

@Service
public class SocketConnectionHandler {

    private String host = "localhost";
    private int port = 9991;

    private ChannelFuture future;

    private EventLoopGroup eventLoopGroup;

    public void openSocketAndSend() throws InterruptedException {
        eventLoopGroup = new NioEventLoopGroup();
        future = openBootStrap().connect(host, port).sync();
    }

    public void send() throws InterruptedException {
        sendData();
    }

    private Bootstrap openBootStrap() {
        Bootstrap b = new Bootstrap();
        int bytes = Long.BYTES;
        b.group(eventLoopGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.handler(new SampleChannelInitializer());
        return b;
    }

    public void shutDown() {
        eventLoopGroup.shutdownGracefully();
    }

    public void sendData() throws InterruptedException {
        SampleRequestPacket sampleRequestPacket =
                new SampleRequestPacket("유호연", "01099536824");
        SamplePacket<SampleRequestPacket> packet = new SamplePacket<>(Command.SAMPLE_REQ,sampleRequestPacket);
        if(future.isSuccess()) {
            future.channel().writeAndFlush(packet).sync();
        }
    }
}
