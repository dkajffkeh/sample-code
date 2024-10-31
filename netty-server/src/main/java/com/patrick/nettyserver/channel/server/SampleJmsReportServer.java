package com.patrick.nettyserver.channel.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrick.nettyserver.channel.handler.JmsConnectHandler;
import com.patrick.tcpprotocol.channel.AbstractServerChannel;
import com.patrick.tcpprotocol.decode.ByteToJmsListenerIdDecoder;
import com.patrick.tcpprotocol.encode.SamplePacketToByteEncoder;
import io.netty.channel.ChannelPipeline;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.stereotype.Component;

@Component
public class SampleJmsReportServer extends AbstractServerChannel {

    private final JmsListenerContainerFactory<?> jmsListenerContainerFactory;

    private final JmsListenerEndpointRegistry registry;

    private final ObjectMapper mapper;

    public SampleJmsReportServer(
            JmsListenerContainerFactory<?> jmsListenerContainerFactory,
            JmsListenerEndpointRegistry registry) {
        this.jmsListenerContainerFactory = jmsListenerContainerFactory;
        this.registry = registry;
        this.mapper = new ObjectMapper();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        super.start(32003);
    }

    @Override
    protected void initChannelPipeLine(ChannelPipeline cp) {

        // Encode outbound handlers
        cp.addLast(new SamplePacketToByteEncoder(mapper));

        // Decode inbound handlers
        cp.addLast(new ByteToJmsListenerIdDecoder());
        cp.addLast(new JmsConnectHandler(jmsListenerContainerFactory, registry));
    }
}
