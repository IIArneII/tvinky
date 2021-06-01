package com.company.model.game;

import com.company.model.entity.Character;
import com.company.model.map.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Game implements Serializable {
    private Map map;
    private HashMap<String, Character> entityDynamicList;

    public Game(Character character){
        this.map = new Map();
        this.entityDynamicList = new HashMap<>();
        this.entityDynamicList.put(character.getName(), character);
        map.getWalls().add(character.getWall());
    }

    public Game(){
        this.map = new Map();
        this.entityDynamicList = new HashMap<>();
    }

    public HashMap<String, Character> getEntityDynamicList(){
        return entityDynamicList;
    }

    public Map getMap(){return this.map;}

    public void addCharacter(Character character){
        entityDynamicList.put(character.getName(), character);
        map.addWall(character.getWall());
    }

    public void updateCharacters(HashMap<String, Character> characters){
        for(java.util.Map.Entry<String, Character> entry: characters.entrySet()){
            entityDynamicList.get(entry.getKey()).updateFrom(entry.getValue());
        }
    }

    public void updateFrom(Game game){
        map = game.map;
        for(java.util.Map.Entry<String, Character> entry: game.entityDynamicList.entrySet()){
            addCharacter(entry.getValue());
        }
    }

    public Game copy(){
        Game temp = new Game();
        temp.map = map.copy();
        for(java.util.Map.Entry<String, Character> entry: entityDynamicList.entrySet()){
            temp.entityDynamicList.put(entry.getKey(), entry.getValue().copy());
        }
        return temp;
    }
}
