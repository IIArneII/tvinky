package com.company.model.client;

import com.company.model.game.Game;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection{
    Socket socket;
    Client client;
    ReadMsg readMsg;
    WriteMsg writeMsg;
    public Connection(Client client, String ip, int port) {
        System.out.println("ConnectionServer");
        try {
            this.client = client;
            socket = new Socket(ip, port);
            writeMsg = new WriteMsg(this, socket, client.getGame());
            readMsg = new ReadMsg(this, socket, client.getGame());
        }
        catch (Exception e) {
            System.out.println("Ошибка при создании ConnectionServer: " + e.getMessage());
        }
    }

    public void start(){
        readMsg.start();
        writeMsg.start();
    }

    public  void stop(){
        readMsg.setLaunched(false);
        writeMsg.setLaunched(false);
    }
}
