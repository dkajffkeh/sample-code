package com.patrick.nettyserver.channel.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.nettyserver.channel.handler.JmsConnectHandler;
import com.patrick.tcpprotocol.channel.AbstractServerChannel;
import com.patrick.tcpprotocol.decode.ByteToRequestDecoder;
import com.patrick.tcpprotocol.encode.SamplePacketToByteEncoder;
import io.netty.channel.ChannelPipeline;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.stereotype.Component;

@Component
public class SampleNettyServer extends AbstractServerChannel {

    private final JmsListenerContainerFactory<?> jmsListenerContainerFactory;
    private final JmsListenerEndpointRegistry registry;

    private final ObjectMapper objectMapper;

    public SampleNettyServer(
            JmsListenerContainerFactory<?> jmsListenerContainerFactory,
            JmsListenerEndpointRegistry registry,
            ObjectMapper objectMapper) {
        this.jmsListenerContainerFactory = jmsListenerContainerFactory;
        this.registry = registry;
        this.objectMapper = objectMapper;
    }


    @Override
    protected void initChannelPipeLine(ChannelPipeline cp) {
        cp.addLast(
                new ByteToRequestDecoder(),
                new JmsConnectHandler(jmsListenerContainerFactory, registry));

        cp.addLast(new SamplePacketToByteEncoder(objectMapper));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        super.start(9991);
    }

}
