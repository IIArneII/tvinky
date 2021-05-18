package com.company.model.server;

import com.company.model.entity.Character;
import com.company.model.game.Game;

import javax.swing.*;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public HashMap<String, Character> allPersonal;
    private static ServerSocket ss;
    private static Game game;
    public void run() {
        try {
            ss = new ServerSocket(1111);
            while (true) {
                Socket socket = ss.accept();
                Connect c = new Connect(this, socket);
                Game game = new Game();
            }

        } catch (Exception e) {}
    }

    public HashMap<String, Character> getAllPersonal() {
        return allPersonal;
    }

    public void setAllPersonal(HashMap<String, Character> u) {
        this.allPersonal = u;
    }
}

