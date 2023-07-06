package org.ysu.beans;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegisterData {
    private String application;
    private Set<String> urls; //高可用
    private Set<String> services; //注册的服务

    public RegisterData(String application, Set<String> services) {
        this.application =application;
        urls = new HashSet<>();
        this.services = services;
    }

    public void addUrl(String url){
        urls.add(url);
    }

    public void removeUrl(String url){
        urls.remove(url);
    }

    public boolean isEmpty(){
        return urls.isEmpty();
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public Set<String> getServices() {
        return services;
    }

    public Boolean hasService(String serviceName){
        return services.contains(serviceName);
    }

    public void setServices(Set<String> services) {
        this.services = services;
    }
}
