package com.company.model.server;

import com.company.model.Message;

import java.net.Socket;

public class ConnectionClient{
    Server server;
    Socket socket;
    ReadMsgServer readMsgServer;
    WriteMsgServer writeMsgServer;
    String name;

    public ConnectionClient (Server server, Socket socket) {
        name = "";
        this.server = server;
        this.socket = socket;
        writeMsgServer = new WriteMsgServer(this, socket);
        readMsgServer = new ReadMsgServer(this, socket);
        readMsgServer.start();
        writeMsgServer.start();
    }

    public void disconnection(){
        server.getConnections().remove(this);
        server.getGame().getEntityDynamicList().remove(name);
        System.out.println("Разрыв соединения с клиентом");
    }
}
