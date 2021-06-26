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
    public static UDPServerWrite serverWrite;
    public static TimeOut timeOut;
    public static DataBase db;

    synchronized public static HashMap<String, ClientInfo> getClients(){
        return clients;
    }

    public UDPServer(int port) throws Exception{
        this.port = port;
        serverSocket = new DatagramSocket(port);
        clients = new HashMap<>();
        game = new Game();
        serverWrite = new UDPServerWrite(port);
        timeOut = new TimeOut();
        db = new DataBase("jdbc:postgresql://localhost:5432/Tvinky", "postgres", "123456789");
    }

    public void run(){
        System.out.println("Сервер запущен");
        serverWrite.start();
        timeOut.start();
        while (true){
            try {
                byte[] buffer = new byte[1000 * 32];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);
                new UDPServerRead(packet).start();
            }
            catch (Exception e){
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
