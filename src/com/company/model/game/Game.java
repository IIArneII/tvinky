package com.company.model.game;

import com.company.model.entity.Character;
import com.company.model.map.Map;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private Map map;
    private HashMap<String, Character> entityDynamicList;

    public Game(Character character){
        this.map = new Map();
        this.entityDynamicList = new HashMap<>();
        this.entityDynamicList.put(character.getName(), character);
    }

    public Game(){
        this.map = new Map();
        this.entityDynamicList = new HashMap<>();
    }

    public HashMap<String, Character> getEntityDynamicList(){
        return entityDynamicList;
    }

    public Map getMap(){return this.map;}

}
