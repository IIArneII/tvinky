package com.company.model.client;

import com.company.model.game.Game;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClientRead extends Thread{
    DatagramSocket socket;
    int port;
    Game game;

    public UDPClientRead(Game game, String ip, int port) throws Exception{
        socket = new DatagramSocket(port);
        this.port = port;
        this.game = game;
    }

    @Override
    public void run(){
        try {
            while (true){
                byte[] buffer = new byte[1000 * 32];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println(msg);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
