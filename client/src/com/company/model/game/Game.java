package com.company.model.game;

import com.company.model.map.Map;

import java.io.Serializable;
import java.util.HashMap;

public class Game implements Serializable, Cloneable {
    private Map map;
    private HashMap<String, Character> entityDynamicList;
    private Character character;

    public Game(Character character){
        this.character = character;
        this.map = new Map();
        this.entityDynamicList = new HashMap<>();
        this.entityDynamicList.put(character.getName(), character);
    }

    public Game(){
        character = new Character();
        this.map = new Map();
        this.entityDynamicList = new HashMap<>();
    }

    public HashMap<String, Character> getEntityDynamicList(){
        return entityDynamicList;
    }

    public Map getMap(){return this.map;}

    public Character getCharacter() {
        return character;
    }

    public void addCharacter(Character character){
        entityDynamicList.put(character.getName(), character);
        map.addWall(character.getWallFront());
        map.addWall(character.getWallLeft());
        map.addWall(character.getWallBehind());
        map.addWall(character.getWallRight());
    }

    public void updateCharacters(HashMap<String, Character> characters){
        for(java.util.Map.Entry<String, Character> entry: characters.entrySet()){
            entityDynamicList.get(entry.getKey()).updateFrom(entry.getValue());
        }
    }

    public void updateFrom(Game game){
        //map = game.map;
        HashMap<String, Character> temp = (HashMap<String, Character>)entityDynamicList.clone();
        temp.remove(character.getName());
        for(java.util.Map.Entry<String, Character> entry: game.entityDynamicList.entrySet()){
            if(!entityDynamicList.containsKey(entry.getValue().getName())){
                addCharacter(entry.getValue());
            }
            else entityDynamicList.get(entry.getKey()).updateFrom(entry.getValue());
            temp.remove(entry.getKey());
        }
        for(java.util.Map.Entry<String, Character> entry: temp.entrySet()){
            map.getWalls().remove(entityDynamicList.get(entry.getKey()).getWallFront());
            map.getWalls().remove(entityDynamicList.get(entry.getKey()).getWallLeft());
            map.getWalls().remove(entityDynamicList.get(entry.getKey()).getWallRight());
            map.getWalls().remove(entityDynamicList.get(entry.getKey()).getWallBehind());
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

    public void shotProcess(Shot shot){
        System.out.println(shot.getClass().getName());
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
