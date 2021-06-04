package com.company.model.entity;

import com.company.model.map.WallCharacter;
import com.company.model.math.Angles;

import java.io.Serializable;

public class Character extends Entity implements Serializable {

    public double MAX_HP = 100;
    private double angCharacter;
    private double health;
    private String name;
    private WallCharacter wallFront;
    private WallCharacter wallBehind;
    private WallCharacter wallLeft;
    private WallCharacter wallRight;

    public Character(){
        super(2, 2, 2);
        this.angCharacter = 0;
        this.health = 100;
        this.name = "player";
        wallFront = new WallCharacter(this, -Angles.Ang45, Angles.Ang45, 0);
        wallLeft = new WallCharacter(this, Angles.Ang45, Angles.Ang135, 2);
        wallBehind = new WallCharacter(this, Angles.Ang135, Angles.Ang225, 2);
        wallRight = new WallCharacter(this, Angles.Ang225, Angles.Ang315, 2);
    }

    public Character(String name){
        super(5, 5, 2);
        this.angCharacter = 0;
        this.health = 100;
        this.name = name;
        wallFront = new WallCharacter(this, -Angles.Ang45, Angles.Ang45, 0);
        wallLeft = new WallCharacter(this, Angles.Ang45, Angles.Ang135, 2);
        wallBehind = new WallCharacter(this, Angles.Ang135, Angles.Ang225, 2);
        wallRight = new WallCharacter(this, Angles.Ang225, Angles.Ang315, 2);
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

    public WallCharacter getWallFront(){
        return wallFront;
    }

    public WallCharacter getWallBehind() {
        return wallBehind;
    }

    public WallCharacter getWallLeft() {
        return wallLeft;
    }

    public WallCharacter getWallRight() {
        return wallRight;
    }

    public void updateFrom(Character character){
        setX(character.getX());
        setY(character.getY());
        this.angCharacter = (character.angCharacter);
    }
}
