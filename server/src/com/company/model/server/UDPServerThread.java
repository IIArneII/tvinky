package com.company.model.server;

import com.company.model.Message;
import com.company.model.game.Character;
import com.company.model.game.Shot;
import com.company.model.math.Angles;
import com.company.model.math.Section;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.DatagramPacket;

public class UDPServerThread extends Thread{
    DatagramPacket packet;

    public UDPServerThread(DatagramPacket packet){
        this.packet = packet;
    }

    @Override
    public void run() {
        String msg = new String(packet.getData(), 0, packet.getLength());
        JSONObject json = (JSONObject) JSONValue.parse(msg);
        if(!UDPServer.clients.containsKey((String) json.get("name"))){
            UDPServer.clients.put((String) json.get("name"),
                    new ClientInfo(packet.getAddress().toString().substring(1), (String) json.get("name")));
            System.out.println("Новый клиент: " + (String) json.get("name") + "   " + this.getName());
            UDPServer.game.addCharacter(new Character((String) json.get("name")));
        }
        else{
            UDPServer.clients.get((String)json.get("name")).timeOut = 0;

            if(json.get("type").equals("characterInfo")){
                Character character = new Character(
                        (String)json.get("name"),
                        (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("x"),
                        (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("y"),
                        (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("angle"),
                        (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("hp"));
                UDPServer.game.updateCharacter(character);
            }
            if(json.get("type").equals("shot")){
                double x = (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("x");
                double y = (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("y");
                double ang = (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("angle");
                Shot shot = new Shot(new Section(x, y,
                        x + Math.cos(Angles.convert(ang)), y + Math.sin(Angles.convert(ang))), null);
                Character character = UDPServer.game.shotProcessing(shot);
                if(character != null){
                    String msg1 = null;
                    if(character.getHealth() < 100){
                        JSONObject json1 = new JSONObject();
                        JSONObject args = new JSONObject();
                        json1.put("type", "changeHP");
                        args.put("hp", character.getHealth());
                        json1.put("args", args);
                        msg1 = json1.toJSONString();
                    }
                    if(character.getHealth() >= 100){
                        JSONObject json1 = new JSONObject();
                        JSONObject args = new JSONObject();
                        json1.put("type", "kill");
                        args.put("hp", character.getHealth());
                        args.put("x", character.getX());
                        args.put("y", character.getY());
                        json1.put("args", args);
                        msg1 = json1.toJSONString();
                    }
                    try {
                        UDPServer.serverWrite.write(UDPServer.getClients().get(character.getName()), msg1);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

/*
ip
name
type
command{
    args{

    }
}
game{
    map
    characters{
        character{
            x
            y
            angle
            hp
            }
        ...
        }
    }
 */