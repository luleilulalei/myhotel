package org.ysu.codec;

import io.netty.handler.codec.http.FullHttpRequest;

import java.util.Map;

public class MyHttpRequest {
    private FullHttpRequest request;
    private Object body;

    public MyHttpRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }

    /**
     * @return the request
     */
    public final FullHttpRequest getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public final void setRequest(FullHttpRequest request) {
        this.request = request;
    }

    /**
     * @return the object
     */
    @SuppressWarnings("unchecked")
    public final String getBody() {
        return (String) body;
    }

    /**
     * @param object the object to set
     */
    public final void setBody(Object body) {
        this.body = body;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HttpJsonRequest [request=" + request + ", body =" + body + "]";
    }
}
