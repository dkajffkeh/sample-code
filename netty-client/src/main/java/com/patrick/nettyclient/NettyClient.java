package com.patrick.nettyclient;

import com.patrick.nettyclient.handler.ClientHandler;
import com.patrick.nettyclient.model.RequestData;
import com.patrick.nettyclient.request.encode.RequestDataEncoder;
import com.patrick.nettyclient.response.decode.ResponseDataDecoder;
import com.patrick.tcpprotocol.protocol.SampleRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.jetbrains.annotations.NotNull;

public class NettyClient {
    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int port = 9991;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(@NotNull SocketChannel ch) {
                    ch.pipeline().addLast(
                            new RequestDataEncoder(),
                            new ResponseDataDecoder(),
                            new ClientHandler());
                }
            });

            ChannelFuture f = b.connect(host, port).sync();
            if(f.isSuccess()){
                SampleRequestPacket sampleRequestPacket = new SampleRequestPacket("유호연", "010-9953-6824");
                f.channel().writeAndFlush(sampleRequestPacket).sync();
            }
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
