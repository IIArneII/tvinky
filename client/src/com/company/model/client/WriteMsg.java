package com.company.model.client;

import com.company.model.Message;
import com.company.model.game.Game;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class WriteMsg extends Thread {
    Connection connectionServer;
    Socket socket;
    ObjectOutputStream writeMsg;
    Game game;

    public WriteMsg(Connection connectionServer, Socket socket, Game game) {
        System.out.println("WriteMsg");
        try {
            this.connectionServer = connectionServer;
            this.socket = socket;
            writeMsg = new ObjectOutputStream(socket.getOutputStream());
            this.game = game;
        } catch (Exception e) {
            System.out.println("Ошибка при создании WriteMsg: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("run WriteMsg");
        try {
            while (true) {
                Thread.currentThread().sleep(1);
                writeMsg.writeObject(new Message("character", connectionServer.client.getCharacter().copy()));
            }
        } catch (Exception e) {
            try {
                socket.close();
            } catch (Exception ee) {
                System.out.println("Ошибка при закрытии сокета: " + e.getMessage());
            }
            System.out.println("Ошибка при отправке сообщения серверу: " + e.getMessage());
        }
    }
}