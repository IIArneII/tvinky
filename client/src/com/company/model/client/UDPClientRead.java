package com.company.model.client;

import com.company.model.game.Character;
import com.company.model.game.Game;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

public class UDPClientRead extends Thread{
    DatagramSocket socket;
    int port;
    Game game;

    public UDPClientRead(Game game, String ip, int port) throws Exception{
        socket = new DatagramSocket(1112);
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
                JSONObject json = (JSONObject) JSONValue.parse(msg);
                if(json.get("type").equals("charactersInfo")){
                    game.updateFrom(getGame(json));
                }
            }
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private Game getGame(JSONObject json){
        Game game = new Game();
        JSONObject g = (JSONObject)json.get("game");
        JSONObject characters = (JSONObject)g.get("characters");

        for(Iterator i = characters.keySet().iterator(); i.hasNext();){
            String key = (String) i.next();
            JSONObject j = (JSONObject) characters.get(key);
            Character character = new Character(
                    (String)j.get("name"),
                    (Double)j.get("x"),
                    (Double)j.get("y"),
                    (Double)j.get("angle"),
                    (Double)j.get("hp"));
            game.addCharacter(character);
        }

//        (Double)((JSONObject)(((JSONObject)(((JSONObject)(j.get("game"))).get("characters"))).get(j.get("name")))).get("x"),
//        (Double)((JSONObject)(((JSONObject)(((JSONObject)(j.get("game"))).get("characters"))).get(j.get("name")))).get("y"),
//        (Double)((JSONObject)(((JSONObject)(((JSONObject)(j.get("game"))).get("characters"))).get(j.get("name")))).get("angle"),
//        (Double)((JSONObject)(((JSONObject)(((JSONObject)(j.get("game"))).get("characters"))).get(j.get("name")))).get("hp"));

        return game;
    }
}
