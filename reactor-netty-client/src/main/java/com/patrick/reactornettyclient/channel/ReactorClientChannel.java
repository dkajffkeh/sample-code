package com.patrick.reactornettyclient.channel;

import static reactor.core.publisher.DirectProcessor.create;

import io.netty.channel.ChannelOption;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.ImmediateEventExecutor;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.MonoToListenableFutureAdapter;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.netty.Connection;
import reactor.netty.NettyOutbound;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.resources.LoopResources;
import reactor.netty.tcp.TcpClient;
import reactor.util.retry.Retry;

public class ReactorClientChannel {

    private final Logger log = LoggerFactory.getLogger(ReactorClientChannel.class);

    private final String host;

    private final int port;

    private final boolean wiretab;

    ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    private final Scheduler scheduler = Schedulers.newParallel("tcp-client-scheduler");

    public ReactorClientChannel(String host, int port, boolean wiretab) {
        this.host = host;
        this.port = port;
        this.wiretab = wiretab;
    }

    public void startup(int maxConns)
            throws ExecutionException, InterruptedException, TimeoutException {
        LoopResources loop = LoopResources.create("report-el", 1, 4, true);

        ConnectionProvider provider = ConnectionProvider.builder("fixed").maxConnections(maxConns)
                .pendingAcquireTimeout(Duration.ofMillis(45_000)).maxIdleTime(Duration.ofMillis(-1))
                .build();

        MonoProcessor<Void> connectMono = MonoProcessor.create();

        TcpClient tcpConn = TcpClient.create(provider)
                .host(host)
                .port(port)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
                .wiretap(true)
                .runOn(loop)
                .doOnConnected(connection -> this.channelGroup.add(connection.channel()))
                .doOnDisconnected(connection -> this.channelGroup.clear());

        tcpConn.handle((inbound, outbound) -> {
                    log.info("Connection established.");
                    final Scheduler scheduler = Schedulers.newParallel("tcp-client-scheduler");
                    scheduler.schedule(() -> outbound.sendObject(Mono.just("Hello")).then().block());
                    return create();
                })
                .connect()
                .doOnNext(updateConnectMono(connectMono))
                .doOnError(updateConnectMono(connectMono))
                .doOnError(e -> {
                    log.info(e.toString());
                })
                .flatMap(Connection::onDispose)             // post-connect issues
                .retryWhen(Retry.from(signals -> signals
                        .map(retrySignal -> (int) retrySignal.totalRetriesInARow())
                        .flatMap(this::reconnect)))
                .repeatWhen(flux -> flux
                        .scan(1, (count, element) -> count++)
                        .flatMap(this::reconnect))
                .subscribe();

        new MonoToListenableFutureAdapter<>(connectMono).get(10, TimeUnit.SECONDS);
    }

    private void linkCheck(NettyOutbound outbound) {
        outbound.sendString(Mono.just("Linked"));
    }

    private Publisher<? extends Long> reconnect(Integer attempt) {
        Long time = 10L;
        return (time != null ? Mono.delay(Duration.ofMillis(time), this.scheduler) : Mono.empty());
    }

    private <T> Consumer<T> updateConnectMono(MonoProcessor<Void> connectMono) {
        return o -> {
            if (!connectMono.isTerminated()) {
                if (o instanceof Throwable) {
                    connectMono.onError((Throwable) o);
                } else {
                    connectMono.onComplete();
                }
            }
        };
    }
}
