package com.company.model.client;

import com.company.model.Message;
import com.company.model.game.Game;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class WriteMsg extends Thread {
    private boolean launched;
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
            launched = false;
        } catch (Exception e) {
            System.out.println("Ошибка при создании WriteMsg: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("run WriteMsg");
        launched = true;
        try {
            while (launched) {
                Thread.currentThread().sleep(1);
                write(new Message("character", connectionServer.client.getCharacter().copy()));
            }
        }
        catch (Exception e) {
            System.out.println("Ошибка при отправке сообщения серверу: " + e.getMessage());
        }
        finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("Ошибка при закрытии сокета: " + e.getMessage());
            }
        }
        System.out.println("Врайт месселж завершился");
    }

    synchronized public void write(Message message) throws Exception{
        writeMsg.writeObject(message);
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    public boolean isLaunched() {
        return launched;
    }
}