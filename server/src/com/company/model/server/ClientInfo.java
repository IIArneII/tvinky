package com.company.model.server;

public class ClientInfo {
    String ip;
    String name;
    int timeOut;

    public ClientInfo(String ip, String name){
        this.ip = ip;
        this.name = name;
        timeOut = 0;
    }
}
