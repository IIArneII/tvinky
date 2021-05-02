package com.company.model;

public class Character extends Entity {

    final public double MAX_HP = 100;
    private double angCharacter;
    private double health;
    private String name;

    public Character(){
        super(5, 5, 2, 1);
        this.angCharacter = 0;
        this.health = 100;
        this.name = "player";
    }

    public Character(String name){
        super(5, 5, 2, 1);
        this.angCharacter = 0;
        this.health = 100;
        this.name = name;
    }

    public double getAngCharacter(){return this.angCharacter;}
    public double getHealth(){return this.health;}

    public void setAngCharacter(double angle){this.angCharacter = angle;}
    public void setHealth(double health){this.health = health;}

}
