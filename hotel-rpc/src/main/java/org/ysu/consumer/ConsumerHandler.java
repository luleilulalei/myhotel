package org.ysu.consumer;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

@ChannelHandler.Sharable
public final class ConsumerHandler extends SimpleChannelInboundHandler<String> implements Callable<String> {
    private String response = null;
    private ChannelHandlerContext context = null;

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    private String requestBody = null;

    @Override
    protected synchronized void channelRead0(ChannelHandlerContext channelHandlerContext, String body) throws Exception {
        response = body;
        notify();
    }

    /**
     * 在这儿发请求
     * @return 响应结果表
     * @throws Exception
     */
    @Override
    public synchronized String call() throws Exception {
        context.writeAndFlush(requestBody);
        wait();
        return response;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
