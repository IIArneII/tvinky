package com.company.model.game;

import com.company.model.game.Character;
import com.company.model.map.Map;
import com.company.model.math.Angles;

import java.io.Serializable;

public class Shot implements Serializable {
    private double x;
    private double y;
    private Map map;
    private Character character;

    public Shot(Map map, double x, double y, Character character){
        this.x = x;
        this.y = y;
        this.map = map;
        this.character = character;
    }

    public void Start(){

        while (map.isWall(x, y) != 1){
            x+= Math.cos(Angles.convert(character.getAng()));
            y+= Math.sin(Angles.convert(character.getAng()));
        }
        if (map.isWall(x, y) == 1){
            System.out.println(x);
            System.out.println(y);
        }
    }

    public double getX() {
        return x;
    }

    public double getY() { return y; }

    public Map getMap() {
        return map;
    }
}
