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

    public UDPClient(Game game, String ip, int port) throws Exception{
        address = InetAddress.getByName(ip);
        socket = new DatagramSocket();
        this.port = port;
        this.game = game;
    }

    @Override
    public void run(){
        try {
            while (true){
                String msg = getInfo().toJSONString();
                byte[] buffer = msg.getBytes(StandardCharsets.UTF_8);
                DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(request);
                this.sleep(1000);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private JSONObject getInfo(){
        JSONObject json = new JSONObject();
        json.put("name", game.getCharacter().getName());
        json.put("ip", address.getHostAddress());
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
