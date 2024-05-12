package com.patrick.nettyclient.api;

import com.patrick.nettyclient.channel.JmsSampleReportClient;
import io.netty.channel.ChannelFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/jms")
@RestController
public class JmsChannelConnectController {

    private ChannelFuture channelFuture;

    @PostMapping("/channel")
    public void connectChannel() throws InterruptedException {
        JmsSampleReportClient client = new JmsSampleReportClient();
        channelFuture = client.connect().await();
        System.out.println(channelFuture.isSuccess());
    }

    @PostMapping("/client")
    public void connectClient() throws InterruptedException {
        if(channelFuture.isSuccess()) {
            channelFuture.channel().writeAndFlush("yhy1011").sync();
        }
    }

}
