package org.ysu.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.util.List;

public class HttpJsonResponseEncoder extends AbstractHttpJsonEncoder<MyHttpResponse> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MyHttpResponse msg, List<Object> out) throws Exception {
        //编码
        ByteBuf body = encode0(ctx, msg.getResult());
        FullHttpResponse response = msg.getHttpResponse();
        if (response == null) {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, body);
        } else {
            response = new DefaultFullHttpResponse(msg.getHttpResponse()
                    .protocolVersion(), msg.getHttpResponse().status(),
                    body);
        }
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/json");
        HttpUtil.setContentLength(response, body.readableBytes());
        out.add(response);
    }
}
