package com.company.model.server;

import com.company.model.game.Character;

public class TimeOut extends Thread{
    public static final int MAX_TIME = 5;

    @Override
    public void run() {
        while (true){
            try {
                for(java.util.Map.Entry<String, ClientInfo> entry: UDPServer.clients.entrySet()){
                    entry.getValue().timeOut++;
                    if(entry.getValue().timeOut > MAX_TIME){
                        System.out.println(entry.getKey() + ": Превышено время ожидания");
                        UDPServer.clients.remove(entry.getKey());
                        Character character = UDPServer.game.getEntityDynamicList().remove(entry.getKey());
                        UDPServer.game.getMap().getWalls().remove(character.getWallBehind());
                        UDPServer.game.getMap().getWalls().remove(character.getWallFront());
                        UDPServer.game.getMap().getWalls().remove(character.getWallLeft());
                        UDPServer.game.getMap().getWalls().remove(character.getWallRight());
                    }
                }
                sleep(1000);
            }
            catch (Exception e){}
        }
    }
}