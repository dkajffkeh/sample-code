package com.patrick.nettyserver.channel.handler;

import com.patrick.nettyserver.channel.consumer.ClientJmsSampleReportConsumer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

public class JmsConnectHandler extends MessageToMessageDecoder<String> {

    private final JmsListenerContainerFactory<?> jmsListenerContainerFactory;
    private final JmsListenerEndpointRegistry registry;

    private ClientJmsSampleReportConsumer clientJmsSampleReportConsumer;

    public JmsConnectHandler(
            JmsListenerContainerFactory<?> jmsListenerContainerFactory,
            JmsListenerEndpointRegistry registry) {
        this.jmsListenerContainerFactory = jmsListenerContainerFactory;
        this.registry = registry;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) {
        String jmsListenerId = msg + "" + System.currentTimeMillis();
        clientJmsSampleReportConsumer = new ClientJmsSampleReportConsumer(ctx);
        SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
        endpoint.setId(jmsListenerId);
        endpoint.setDestination(msg);
        endpoint.setMessageListener(clientJmsSampleReportConsumer);
        registry.registerListenerContainer(endpoint, jmsListenerContainerFactory, true);
    }
}
