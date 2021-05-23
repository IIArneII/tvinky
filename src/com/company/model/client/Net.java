package com.company.model.client;

import com.company.model.game.Game;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Net {
    Socket s;
    Client client;
    public Net (Client client, String ip, int port)
    {
        System.out.println("Net");
        try
        {
            this.client = client;
            s = new Socket(ip, port);
        }
        catch (Exception e) { }
    }
}
