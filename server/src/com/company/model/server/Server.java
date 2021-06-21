package com.company.model.server;

import com.company.model.Message;
import com.company.model.game.Game;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{

    public static ArrayList<Connection> connections;
    public static DataBase db;
    public static Game game;
    public static ServerSocket serverSocket;

    public Server() {
        System.out.println("Server");
        try {
            //novi48001
            db = new DataBase("jdbc:postgresql://localhost:5432/Tvinky", "postgres", "123456789");
            connections = new ArrayList<>();
            serverSocket = new ServerSocket(1111);
            game = new Game();
        }
        catch (Exception e) {
            System.out.println("Ошибка при создании Server: " + e.getMessage());
        }
    }

    public void start(){
        System.out.println("Server.run()");
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Подключение клиента: " + socket.getInetAddress().toString());
                connections.add(new Connection(socket));
                System.out.println("Подключений: " + connections.size());
            }
        }
        catch (Exception e){
            System.out.println("Ошибка в Server.start(): " + e.getMessage());
        }
    }

    synchronized public static void writeMsgAll(Message message){
        for(int i = 0; i < connections.size(); i++){
            try {
                connections.get(i).write(message);
            }
            catch (Exception e){
                System.out.println("Ошибка при отправке сообщения всем клиентам: " + e.getMessage());
            }
        }
    }

    synchronized public static void writeMsgOne(Message  message, String name){
        for(int i = 0; i < connections.size(); i++){
            try {
                if(connections.get(i).getPlayerName().equals(name)) connections.get(i).write(message);
            }
            catch (Exception e){
                System.out.println("Ошибка при отправке сообщения всем клиентам: " + e.getMessage());
            }
        }
    }
}

