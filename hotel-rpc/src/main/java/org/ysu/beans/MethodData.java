package org.ysu.beans;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.ysu.utils.FastJsonUtil;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodData {
    private String application;
    private String service;
    private String method;
    private Object[] args;

    public MethodData(String application, String service, String method, Object... args) {
        this.application = application;
        this.service = service;
        this.method = method;
        this.args = args;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }


    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }



    @Override
    public String toString() {
        return "MethodData{" +
                "application='" + application + '\'' +
                ", service='" + service + '\'' +
                ", method=" + method +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
