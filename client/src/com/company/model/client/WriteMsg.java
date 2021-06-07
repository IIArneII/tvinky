package com.company.model.client;

import com.company.model.Message;
import com.company.model.game.Game;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class WriteMsg extends Thread {
    private boolean launched;
    private Socket socket;
    private ObjectOutputStream out;
    private Game game;

    public WriteMsg(Socket socket, Game game) {
        try {
            this.socket = socket;
            out = new ObjectOutputStream(socket.getOutputStream());
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
                this.sleep(1);
                write(new Message("character", game.getCharacter().copy()));
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
    }

    synchronized public void write(Message message) throws Exception{
        out.writeObject(message);
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    public boolean isLaunched() {
        return launched;
    }
}