package org.ysu.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.ysu.utils.FastJsonUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class AbstractHttpJsonEncoder<T> extends MessageToMessageEncoder<T> {
    final static Charset UTF_8 = StandardCharsets.UTF_8;

    protected ByteBuf encode0(ChannelHandlerContext ctx, Object body) {
        String jsonStr = FastJsonUtil.convertObjectToJSON(body);
        ByteBuf encodeBuf = Unpooled.copiedBuffer(jsonStr, UTF_8);
        return encodeBuf;
    }

}
