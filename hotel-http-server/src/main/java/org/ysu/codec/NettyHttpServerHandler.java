package org.ysu.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.CharSetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ysu.ioc.IocContainer;
import org.ysu.server.DispatchController;

import java.net.URI;

public class NettyHttpServerHandler extends SimpleChannelInboundHandler<MyHttpRequest> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyHttpServerHandler.class);

    /**
     * TODO 读取客户端数据
     *
     * @param ctx        上下文
     * @param httpObject 客户端和服务器端通讯的数据被封装成 HttpObject 类型
     * @throws Exception
     * @implNote SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter 的子类
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyHttpRequest httpObject) throws Exception {
        System.out.println(ctx.getClass());
        URI uri = new URI(httpObject.getRequest().uri());
        ByteBuf content;
        if("/login".equals(uri.getPath())){
            content = Unpooled.copiedBuffer("success", CharsetUtil.UTF_8);
        }else{
            Object result = DispatchController.handlerPath(uri.getPath(), httpObject);
            String resultString = JSONObject.toJSONString(result);
            content = Unpooled.copiedBuffer(resultString, CharsetUtil.UTF_8);
        }
        //构建 HttpResponse
        DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                content);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json")
                .set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes())
                .set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "*")
                .set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        ctx.writeAndFlush(httpResponse);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.BAD_REQUEST);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json")
                .set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "*")
                .set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        ctx.writeAndFlush(httpResponse);
    }
}
