package com.company.model.server;

import com.company.model.game.Character;
import com.company.model.game.Game;
import org.json.simple.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPServerWrite extends Thread{
    DatagramSocket socket;
    int port;
    int clientPort;

    public UDPServerWrite(int port) throws Exception{
        System.out.println("UDPServerWrite");
        socket = new DatagramSocket();
        this.port = port;
        clientPort = 1112;
    }

    @Override
    public void run() {
        try {
            System.out.println("UDPServerWrite start");
            while (true){
                for(java.util.Map.Entry<String, ClientInfo> entry: UDPServer.clients.entrySet()){
                    String msg = getInfo().toJSONString();
                    write(entry.getValue(), msg);
                }
                this.sleep(1);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void write(ClientInfo client, String msg) throws Exception{
        byte[] buffer = msg.getBytes(StandardCharsets.UTF_8);
        DatagramPacket request = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(client.ip), clientPort);
        socket.send(request);
    }

    private JSONObject getInfo(){
        JSONObject json = new JSONObject();
        json.put("type", "charactersInfo");
        JSONObject game = new JSONObject();
        JSONObject characters = new JSONObject();
        for(java.util.Map.Entry<String, Character> entry: UDPServer.game.getEntityDynamicList().entrySet()){
            JSONObject character = new JSONObject();
            character.put("x", entry.getValue().getX());
            character.put("y", entry.getValue().getY());
            character.put("angle", entry.getValue().getAng());
            character.put("hp", entry.getValue().getHealth());
            characters.put(entry.getKey(), character);
        }
        game.put("characters", characters);
        json.put("game", game);
        return json;
    }
}
