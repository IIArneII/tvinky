package com.company.model.server;

import com.company.model.entity.Character;

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
    private static Socket s;
    private static InputStream in;
    private static OutputStream out;
    public void run() {
        try {
            ss = new ServerSocket(1111);
            while (true) {
                Socket socket = ss.accept();
                Connect c = new Connect(this, socket);
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

