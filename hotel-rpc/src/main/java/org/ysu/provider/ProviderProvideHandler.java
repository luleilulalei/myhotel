package org.ysu.provider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ysu.beans.MethodData;
import org.ysu.ioc.IocContainer;
import org.ysu.utils.FastJsonUtil;
import org.ysu.utils.ReflectionUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable
public class ProviderProvideHandler extends SimpleChannelInboundHandler<String>{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderProvideHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        MethodData methodData = (MethodData)FastJsonUtil.convertJsonToObject(s, MethodData.class);
        LOGGER.info(methodData.toString());
        Object bean = IocContainer.getBean(methodData.getService());

        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodData.getMethod())){
                LOGGER.info(Arrays.toString(methodData.getArgs()));
                LOGGER.info(method.toString());
                Object[] args = new Object[methodData.getArgs().length];

                //格式化 参数
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < methodData.getArgs().length; i++) {
                    if (methodData.getArgs()[i] instanceof JSONObject) {
                        String jsonArg = FastJsonUtil.convertObjectToJSON(methodData.getArgs()[i]);
                        args[i] = FastJsonUtil.convertJsonToObject(jsonArg, parameterTypes[i]);
                    }else if(methodData.getArgs()[i] instanceof JSONArray){
                        String jsonArg = FastJsonUtil.convertObjectToJSON(methodData.getArgs()[i]);
                    } else {
                        args[i] = methodData.getArgs()[i];
                    }
                }

                Object res = ReflectionUtil.invokeMethod(bean, method, args);
                Map<String, Object> response = new HashMap<>();
                LOGGER.info("method: "+ method);
                response.put("msg", "success");
                response.put("data", FastJsonUtil.convertObjectToJSON(res));
                LOGGER.info("处理结果 "+res);
                LOGGER.info("响应结果 "+response);
                ctx.writeAndFlush(FastJsonUtil.collectToString(response));
            }
        }
    }

//    public void resetArgs(){
//        for (int i = 0; i < this.args.length; i++) {
//            if(this.args[i] instanceof JSONObject){
//                String jsonStr = FastJsonUtil.convertObjectToJSON(this.args[i]);
//
//            }
//        }
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
