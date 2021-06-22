package com.company.model.server;

import com.company.model.game.Game;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;

public class UDPServer {
    public static Game game;
    int port;
    public static DatagramSocket serverSocket;
    public static HashMap<String, ClientInfo> clients;
    UDPServerWrite serverWrite;

    public UDPServer(int port) throws Exception{
        this.port = port;
        serverSocket = new DatagramSocket(port);
        clients = new HashMap<>();
        game = new Game();
        serverWrite = new UDPServerWrite(port);
    }

    public void run(){
        System.out.println("Сервер запущен");
        serverWrite.start();
        while (true){
            try {
                byte[] buffer = new byte[1000 * 32];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);
                new UDPServerThread(packet).start();
            }
            catch (Exception e){
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
