package com.company.model;
import com.company.model.server.UDPServer;

public class Main{
    public static void main(String[] args) {
        try {
            UDPServer server = new UDPServer(1111);
            server.run();
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
