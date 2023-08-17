package com.patrick.nettyclient;

import com.patrick.nettyclient.handler.ClientHandler;
import com.patrick.nettyclient.model.RequestData;
import com.patrick.nettyclient.request.encode.RequestDataEncoder;
import com.patrick.nettyclient.response.decode.ResponseDataDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                public void initChannel(SocketChannel ch)
                        throws Exception {
                    ch.pipeline().addLast(
                            new RequestDataEncoder(),
                            new ResponseDataDecoder(),
                            new ClientHandler());
                }
            });

            ChannelFuture f = b.connect(host, port).sync();
            if(f.isSuccess()){
                RequestData requestData = new RequestData();
                requestData.setStringValue("helloWorld");
                requestData.setIntValue(123);
                f.channel().writeAndFlush(requestData).sync();
            }
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
