package org.ysu.register;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class RegisterCenterStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCenterStarter.class);
    private static final Integer REGISTER_PORT = 9999;

//    public static void main(String[] args) {
//        handler = new RegisterCenterHandler();
//        run(REGISTER_PORT, handler);
//    }

    public void start(int port){
        final ChannelHandler handler = new RegisterCenterHandler();
        run(port, handler);
    }

    public void run(int port, final ChannelHandler personalHandler){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(2);

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 3)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            pipeline.addLast(personalHandler);
                        }
                    });
            ChannelFuture future = bootstrap.bind(new InetSocketAddress(port));
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
