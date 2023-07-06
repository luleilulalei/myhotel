package org.ysu.provider;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

@ChannelHandler.Sharable
public final class ProviderRegisterHandler extends SimpleChannelInboundHandler<String> implements Callable<String> {
    public void setBody(String body) {
        this.body = body;
    }

    private String body;
    private ChannelHandlerContext context;
    private String response;
    private final Object lock = new Object();

    @Override
    protected synchronized void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
//            Map<Object, Object> map = FastJsonUtil.stringToCollect(s);
//            System.out.println(map);
        response = s;
        notify();
    }

    @Override
    public synchronized String call() throws Exception {
        context.writeAndFlush(body);
        wait();
        return response;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }
}
