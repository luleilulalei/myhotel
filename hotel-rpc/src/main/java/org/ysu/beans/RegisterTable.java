package org.ysu.beans;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterTable {
    public void setRegisterTable(Map<String, RegisterData> registerTable) {
        this.registerTable = registerTable;
    }

    private Map<String, RegisterData> registerTable = new HashMap<>();

    public List<String> getServices(String application) {
        Set<String> services = registerTable.get(application).getServices();
        return new ArrayList<>(services);
    }

    public List<String> getAvailableApplication(String application) {
        return new ArrayList<>(registerTable.get(application).getUrls());
    }

    public List<String> getApplications() {
        return new ArrayList<>(registerTable.keySet());
    }

}
