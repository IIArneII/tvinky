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
    private DataBase db;
    public boolean ConnectDataBase;

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
            //process.start();
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

    public DataBase getDb() {
        return db;
    }

    public ArrayList<ConnectionClient> getConnections() {
        return connections;
    }

    synchronized public void writeMsgAll(Message message){
        for(int i = 0; i < connections.size(); i++){
            try {
                connections.get(i).writeMsgServer.write(message);
            }
            catch (Exception e){
                System.out.println("Ошибка при отправке сообщения всем клиентам: " + e.getMessage());
            }
        }
    }


    /*public GameProcess getProcess() {
        return process;
    }*/
}

