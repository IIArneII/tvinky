package com.company.model.client;

import com.company.model.game.Game;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Net {
    private static Game game;

    public static void main(String args[])
    {
        try
        {
            Socket s = new Socket("127.0.0.1",1111);
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();
            game = new Game();
        }
        catch (Exception e) { }
    }
}
