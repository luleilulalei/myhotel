package org.ysu.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.ysu.codec.HttpJsonRequestDecoder;
import org.ysu.codec.HttpJsonResponseEncoder;
import org.ysu.codec.NettyHttpServerHandler;

import java.util.Map;

public class NettyHttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //向管道加入处理器
        //得到管道
        ChannelPipeline pipeline = socketChannel.pipeline();


        //加入一个 netty 提供的 httpServerCodec codec => [coder - decoder]
//        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
//        pipeline.addLast("MyHttpServerCode2c", new HttpJsonRequestDecoder(Map.class));
        //接收HttpJsonRequest，需要对应解码器
        //ByteBuf->FullHttpRequest-> HttpJsonRequestDecoder
        //输出HttpJsonResponse，需要对应编码器
        //HttpResponseEncoder->FullHttpResponse-> HttpJsonResponseEncoder
        pipeline.addLast("http-decoder", new HttpRequestDecoder());
        pipeline.addLast("http-aggregator", new HttpObjectAggregator(65536));
        pipeline.addLast("json-decoder", new HttpJsonRequestDecoder(true));
        pipeline.addLast("http-encoder", new HttpResponseEncoder());
        pipeline.addLast("json-encoder", new HttpJsonResponseEncoder());
        pipeline.addLast("myServerHandler", new NettyHttpServerHandler());
    }
}
