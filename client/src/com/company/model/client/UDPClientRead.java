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
    boolean launched;

    public UDPClientRead(Game game, String ip, int port) throws Exception{
        socket = new DatagramSocket(1112);
        this.port = port;
        this.game = game;
        launched = false;
    }

    @Override
    public void run(){
        launched = true;
        try {
            while (launched){
                Client.timeOut.timeout = 0;
                byte[] buffer = new byte[1000 * 32];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                JSONObject json = (JSONObject) JSONValue.parse(msg);

                if(json.get("type").equals("charactersInfo")){
                    Game game = getGame(json);
                    game.getEntityDynamicList().remove(this.game.getCharacter().getName());
                    this.game.updateFrom(game);
                }
                if(json.get("type").equals("changeXY")){
                    JSONObject args = (JSONObject) json.get("args");
                    game.getCharacter().setX((Double) args.get("x"));
                    game.getCharacter().setY((Double) args.get("y"));
                }
                if(json.get("type").equals("changeHP")){
                    JSONObject args = (JSONObject) json.get("args");
                    game.getCharacter().setHealth((Double) args.get("hp"));
                }
                if(json.get("type").equals("kill")){
                    JSONObject args = (JSONObject) json.get("args");
                    game.getCharacter().setHealth((Double) args.get("hp"));
                    game.getCharacter().setX((Double) args.get("x"));
                    game.getCharacter().setY((Double) args.get("y"));
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
                    key,
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
