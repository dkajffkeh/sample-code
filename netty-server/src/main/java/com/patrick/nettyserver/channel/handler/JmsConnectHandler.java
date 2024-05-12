package com.patrick.nettyserver.channel.handler;

import com.patrick.nettyserver.channel.consumer.ClientJmsSampleReportConsumer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.AttributeKey;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

public class JmsConnectHandler extends MessageToMessageDecoder<String> {

    private final JmsListenerContainerFactory<?> jmsListenerContainerFactory;
    private final JmsListenerEndpointRegistry registry;

    private ClientJmsSampleReportConsumer clientJmsSampleReportConsumer;

    private final Logger LOGGER = LoggerFactory.getLogger(JmsConnectHandler.class);

    public JmsConnectHandler(
            JmsListenerContainerFactory<?> jmsListenerContainerFactory,
            JmsListenerEndpointRegistry registry) {
        this.jmsListenerContainerFactory = jmsListenerContainerFactory;
        this.registry = registry;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) {
        if(clientJmsSampleReportConsumer == null) {
            String jmsListenerId = msg + "" + System.currentTimeMillis();
            clientJmsSampleReportConsumer = new ClientJmsSampleReportConsumer(ctx);
            SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
            endpoint.setId(jmsListenerId);
            endpoint.setDestination(msg);
            endpoint.setMessageListener(clientJmsSampleReportConsumer);
            endpoint.setConcurrency("1");
            registry.registerListenerContainer(endpoint, jmsListenerContainerFactory, true);

            ctx.channel().attr(AttributeKey.valueOf("JMS_LISTENER_ID")).set(jmsListenerId);
        }
    }

    @Override
    public void channelInactive(@NotNull ChannelHandlerContext ctx) throws Exception {
        String jmsListenerId = (String) ctx.channel().attr(AttributeKey.valueOf("JMS_LISTENER_ID")).get();
        ((DisposableBean) Objects.requireNonNull(registry.getListenerContainer(jmsListenerId))).destroy();
        super.channelInactive(ctx);
    }
}
