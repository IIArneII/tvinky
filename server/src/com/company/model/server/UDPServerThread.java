package com.company.model.server;

import com.company.model.game.Character;
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
            System.out.println("Новый клиент: " + (String) json.get("ip"));
            UDPServer.game.addCharacter(new Character((String) json.get("name")));
        }
        else{
            if(json.get("type").equals("characterInfo")){
                Character character = new Character(
                        (String)json.get("name"),
                        (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("x"),
                        (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("y"),
                        (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("angle"),
                        (Double)((JSONObject)(((JSONObject)(((JSONObject)(json.get("game"))).get("characters"))).get(json.get("name")))).get("hp"));
                UDPServer.game.updateCharacter(character);
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