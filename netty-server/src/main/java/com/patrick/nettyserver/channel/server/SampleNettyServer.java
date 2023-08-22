package com.patrick.nettyserver.channel.server;

import com.patrick.tcpprotocol.channel.AbstractServerChannel;
import org.springframework.stereotype.Component;

@Component
public class SampleNettyServer extends AbstractServerChannel {

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
    }

}
