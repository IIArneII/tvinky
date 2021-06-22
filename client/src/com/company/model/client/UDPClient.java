package com.company.model.client;

import com.company.model.game.Game;
import org.json.simple.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPClient extends Thread{
    InetAddress address;
    DatagramSocket socket;
    int port;
    Game game;
    boolean launched;

    public UDPClient(Game game, String ip, int port) throws Exception{
        address = InetAddress.getByName(ip);
        socket = new DatagramSocket();
        this.port = port;
        this.game = game;
        launched = false;
    }

    @Override
    public void run(){
        launched = true;
        try {
            String msg = getInfo().toJSONString();
            byte[] buffer = msg.getBytes(StandardCharsets.UTF_8);
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(request);
            this.sleep(100);

            while (launched){
                String msg1 = getInfo().toJSONString();
                write(msg1);
                this.sleep(1);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void write(String msg) throws Exception{
        byte[] buffer = msg.getBytes(StandardCharsets.UTF_8);
        DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(request);
    }

    public JSONObject getInfo(){
        JSONObject json = new JSONObject();
        json.put("name", game.getCharacter().getName());
        json.put("type", "characterInfo");
        JSONObject game = new JSONObject();
        JSONObject characters = new JSONObject();
        JSONObject character = new JSONObject();
        character.put("x", this.game.getCharacter().getX());
        character.put("y", this.game.getCharacter().getY());
        character.put("angle", this.game.getCharacter().getAng());
        character.put("hp", this.game.getCharacter().getHealth());
        characters.put(this.game.getCharacter().getName(), character);
        game.put("characters", characters);
        json.put("game", game);
        return json;
    }
}
