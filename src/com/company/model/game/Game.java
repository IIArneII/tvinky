package com.company.model;

import com.company.model.map.Map;

import java.util.ArrayList;

public class Game {
    private Map map;
    private ArrayList<Character> entityDynamicList;

    public Game(){
        this.map = new Map();
        this.entityDynamicList = new ArrayList<>();
        this.entityDynamicList.add(new Character());
    }


    public ArrayList<Character> getEntityDynamicList(){return this.entityDynamicList;}
    public Map getMap(){return this.map;}

}
