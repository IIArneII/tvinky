package com.company.model.server;

import com.company.model.Message;
import com.company.model.game.Game;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{

    private ServerSocket serverSocket;
    private ArrayList<ConnectionClient> connections;
    private Game game;
    public static boolean ConnectDataBase;
    public Server() {
        System.out.println("Server");
        try {
            connections = new ArrayList<>();
            serverSocket = new ServerSocket(1111);
            game = new Game();
        }
        catch (Exception e) {
            System.out.println("Ошибка при создании Server: " + e.getMessage());
        }
    }

    public void start(){
        try {
            DataBase.ConnectionWithDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Server.run()");
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Подключение клиента");
                connections.add(new ConnectionClient(this, socket));
                System.out.println(connections.size());
            }
        }
        catch (Exception e){
            System.out.println("Ошибка в Server.start(): " + e.getMessage());
        }
    }

    public Game getGame() {
        return game;
    }

    public ArrayList<ConnectionClient> getConnections() {
        return connections;
    }

    public void writeMsgAll(Message message){
        for(int i = 0; i < connections.size(); i++){
            connections.get(i).writeMsgServer.writeMsg(message);
        }
    }

    public static void Answer (){
        if (ConnectDataBase) {
            System.out.println("Успешное подключение к базе данных");
        }
        else {
            System.out.println("Не удалось подключиться к базе данных");
        }
    }
}

