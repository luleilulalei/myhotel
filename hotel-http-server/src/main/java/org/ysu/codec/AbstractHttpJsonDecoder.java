package org.ysu.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.StringUtils;
import org.ysu.utils.FastJsonUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by carl.yu on 2016/12/16.
 */
public abstract class AbstractHttpJsonDecoder<T> extends MessageToMessageDecoder<T> {
//    private Class<?> clazz;
    private boolean isPrint;
    private final static Charset UTF_8 = CharsetUtil.UTF_8;

    protected AbstractHttpJsonDecoder() {
        this(false);
    }

    protected AbstractHttpJsonDecoder(boolean isPrint) {
        this.isPrint = isPrint;
    }

    protected Object decode0(ChannelHandlerContext ctx, ByteBuf body) {
        String content = body.toString(UTF_8);
        if (isPrint)
            System.out.println("The body is : " + content);
        if(StringUtils.isEmpty(content)){
            content = "{}";
        }
        return content;
//        return FastJsonUtil.stringToCollect(content);
    }

}