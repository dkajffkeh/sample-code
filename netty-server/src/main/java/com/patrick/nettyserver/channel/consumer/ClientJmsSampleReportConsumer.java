package com.patrick.nettyserver.channel.consumer;

import com.patrick.tcpprotocol.message.JmsHelloMessage;
import com.patrick.tcpprotocol.protocol.SamplePacket;
import com.patrick.tcpprotocol.protocol.code.Command;
import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.ExecutionException;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class ClientJmsSampleReportConsumer implements MessageListener {

    private ChannelHandlerContext context;

    public ClientJmsSampleReportConsumer(ChannelHandlerContext context) {
        this.context = context;
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            JmsHelloMessage jmsHelloMessage = (JmsHelloMessage) objectMessage.getObject();
            context.writeAndFlush(new SamplePacket<JmsHelloMessage>(Command.SAMPLE_REQ, jmsHelloMessage)).get();
        } catch (JMSException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
