package com.company.model.game;

import com.company.model.math.Angles;
import com.company.model.math.Point;
import com.company.model.map.WallCharacter;

import java.io.Serializable;

public class Character implements Serializable, Cloneable {

    final public double MAX_HP = 100;
    private Point xy;
    private double ang;
    private double health;
    private String name;
    private WallCharacter wallFront;
    private WallCharacter wallBehind;
    private WallCharacter wallLeft;
    private WallCharacter wallRight;

    public Character(){
        xy = new Point(6, 2);
        this.ang = 0;
        this.health = 100;
        this.name = "player";
        wallFront = new WallCharacter(this, -Angles.Ang45, Angles.Ang45, 0);
        wallLeft = new WallCharacter(this, Angles.Ang45, Angles.Ang135, 2);
        wallBehind = new WallCharacter(this, Angles.Ang135, Angles.Ang225, 2);
        wallRight = new WallCharacter(this, Angles.Ang225, Angles.Ang315, 2);
    }

    public Character(String name){
        xy = new Point(6, 2);
        this.ang = 0;
        this.health = 100;
        this.name = name;
        wallFront = new WallCharacter(this, -Angles.Ang45, Angles.Ang45, 0);
        wallLeft = new WallCharacter(this, Angles.Ang45, Angles.Ang135, 2);
        wallBehind = new WallCharacter(this, Angles.Ang135, Angles.Ang225, 2);
        wallRight = new WallCharacter(this, Angles.Ang225, Angles.Ang315, 2);
    }

    public Character(String name, double x, double y, double ang, double health){
        xy = new Point(x, y);
        this.ang = ang;
        this.health = health;
        this.name = name;
        wallFront = new WallCharacter(this, -Angles.Ang45, Angles.Ang45, 0);
        wallLeft = new WallCharacter(this, Angles.Ang45, Angles.Ang135, 2);
        wallBehind = new WallCharacter(this, Angles.Ang135, Angles.Ang225, 2);
        wallRight = new WallCharacter(this, Angles.Ang225, Angles.Ang315, 2);
    }

    public Point getXY() {
        return xy;
    }

    public double getX() {
        return xy.getX();
    }

    public double getY() {
        return xy.getY();
    }

    public void setX(double x){
        xy.setX(x);
    }

    public void setY(double y){
        xy.setY(y);
    }

    public double getAng() {
        return this.ang;
    }

    public void setAng(double ang) {
        this.ang = ang;
    }

    public double getHealth() {
        return this.health;}


    public void setHealth(double health){this.health = health;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character copy(){
        Character temp = new Character(this.getName());
        temp.setAng(this.ang);
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
        this.ang = (character.ang);
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
