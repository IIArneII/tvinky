package com.company.model.game;

import com.company.model.entity.Character;
import com.company.model.map.Map;

import java.io.Serializable;
import java.util.HashMap;

public class Game implements Serializable {
    private Map map;
    private HashMap<String, Character> entityDynamicList;
    private String nameCharacter;

    public Game(Character character){
        nameCharacter = character.getName();
        this.map = new Map();
        this.entityDynamicList = new HashMap<>();
        this.entityDynamicList.put(character.getName(), character);
        map.getWalls().add(character.getWall());
    }

    public Game(){
        nameCharacter = "";
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
        HashMap<String, Character> temp = (HashMap<String, Character>)entityDynamicList.clone();
        temp.remove(nameCharacter);
        for(java.util.Map.Entry<String, Character> entry: game.entityDynamicList.entrySet()){
            if(!entityDynamicList.containsKey(entry.getValue().getName())){
                System.out.println("Добавил");
                addCharacter(entry.getValue());
            }
            else entityDynamicList.get(entry.getKey()).updateFrom(entry.getValue());
            temp.remove(entry.getKey());
        }
        for(java.util.Map.Entry<String, Character> entry: temp.entrySet()){
            map.getWalls().remove(entityDynamicList.get(entry.getKey()).getWall());
            entityDynamicList.remove(entry.getKey());
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
