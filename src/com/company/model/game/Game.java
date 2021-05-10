package com.company.model.game;

import com.company.model.entity.Character;
import com.company.model.map.Map;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private Map map;
    //private ArrayList<Character> entityDynamicList;
    private HashMap<String, Character> entityDynamicList;

    public Game(){
        this.map = new Map();
        //this.entityDynamicList = new ArrayList<>();
        this.entityDynamicList = new HashMap<>();
        this.entityDynamicList.put("player", new Character());
    }


    //public ArrayList<Character> getEntityDynamicList(){return this.entityDynamicList;}
    public HashMap<String, Character> getEntityDynamicList(){
        return entityDynamicList;
    }

    public Map getMap(){return this.map;}

}
