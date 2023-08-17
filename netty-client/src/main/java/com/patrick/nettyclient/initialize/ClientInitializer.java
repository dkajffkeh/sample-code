package com.patrick.nettyclient.initialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    private ObjectMapper mapper;


    public ClientInitializer() {
        mapper = new ObjectMapper();
    }
    
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
    }
}
