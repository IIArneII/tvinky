package com.company.model.server;

import java.net.Socket;
public class Connect{
    Server s;
    Socket socket;
    public Connect (Server s, Socket socket) {
        this.s = s;
        this.socket = socket;
    }
}
