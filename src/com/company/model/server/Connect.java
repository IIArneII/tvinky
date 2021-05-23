package com.company.model.server;

import java.net.Socket;
public class Connect{
    Server s;
    Socket socket;
    InputStream in;
    OutputStream out;
    public Connect (Server s, Socket socket) {
        System.out.println("Connect");
        this.s = s;
        this.socket = socket;
        in = new InputStream(this, socket, s.getGame());
        out = new OutputStream(this, socket, s.getGame());
    }
}
