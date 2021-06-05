package com.company.model.server;

import com.company.model.Message;
import com.company.model.Shot;
import com.company.model.entity.Character;
import com.company.model.game.Game;
import com.company.model.map.WallCharacter;
import com.company.model.math.RayCasting;

import java.util.ArrayList;
import java.util.HashMap;

public class GameProcess extends Thread{
    private ArrayList<Shot> shots;
    private Game game;
    private Server server;

    public GameProcess(Game game, Server server){
        shots = new ArrayList<>();
        this.game = game;
        this.server = server;
    }

    public void addShot(Shot shot){
        shots.add(shot);
    }

    @Override
    public void run(){
        try {
            while(true){
                this.sleep(1);
                if(!shots.isEmpty()){
                    System.out.println("Выстрел вызван");
                    ArrayList<WallCharacter> targetsWall = new ArrayList<>();
                    for(java.util.Map.Entry<String, Character> entry: game.getEntityDynamicList().entrySet()){
                        if(!entry.getKey().equals(shots.get(0).getCharacter().getName())){
                            targetsWall.add(entry.getValue().getWallRight());
                            targetsWall.add(entry.getValue().getWallBehind());
                            targetsWall.add(entry.getValue().getWallLeft());
                            targetsWall.add(entry.getValue().getWallRight());
                        }
                    }
                    HashMap<String, Character> targets = RayCasting.rayCastingShot(shots.get(0).getSection(), targetsWall);
                    for(java.util.Map.Entry<String, Character> entry: targets.entrySet()){
                        entry.getValue().setHealth(entry.getValue().getHealth() - 20);
                        if(entry.getValue().getHealth() < 0){
                            Character character = entry.getValue().copy();
                            character.setHealth(100);
                            character.setX(0);
                            character.setY(0);
                            server.writeMsgAll(new Message("changeXY", character));
                        }
                    }
                    shots.remove(0);
                }
            }
        }
        catch (Exception e){
            System.out.println("Ошибка в потоке игры: " + e.getMessage());
        }
    }
}
