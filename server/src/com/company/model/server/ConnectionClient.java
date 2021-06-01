package com.company.model.server;

import java.net.Socket;

public class ConnectionClient{
    Server server;
    Socket socket;
    ReadMsgServer readMsgServer;
    WriteMsgServer writeMsgServer;

    public ConnectionClient (Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        writeMsgServer = new WriteMsgServer(this, socket);
        readMsgServer = new ReadMsgServer(this, socket);
        readMsgServer.start();
    }
}
