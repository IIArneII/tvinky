package com.company.model.entity;

import com.company.model.map.WallCharacter;
import com.company.model.math.Section;

import java.io.Serializable;

public class Character extends Entity implements Serializable {

    public double MAX_HP = 100;
    private double angCharacter;
    private double health;
    private String name;
    private WallCharacter wall;

    public Character(){
        super(2, 2, 2, 1);
        this.angCharacter = 0;
        this.health = 100;
        this.name = "player";
        wall = new WallCharacter(this);
    }

    public Character(String name){
        super(5, 5, 2, 1);
        this.angCharacter = 0;
        this.health = 100;
        this.name = name;
        wall = new WallCharacter(this);
    }

    public double getAngCharacter(){return this.angCharacter;}

    public double getHealth(){return this.health;}

    public void setAngCharacter(double angle){
        this.angCharacter = angle;
    }

    public void setHealth(double health){this.health = health;}

    public String getName() {
        return name;
    }

    public Character copy(){
        Character temp = new Character(this.getName());
        temp.setAngCharacter(this.angCharacter);
        temp.setHealth(this.getHealth());
        temp.setX(this.getX());
        temp.setY(this.getY());
        return temp;
    }

    public WallCharacter getWall(){
        wall.setSection(new Section(getX(), getY(), getX() + 1, getY() + 1));
        return wall;
    }

    public void updateFrom(Character character){
        setX(character.getX());
        setY(character.getY());
        this.angCharacter = (character.angCharacter);
    }
}
