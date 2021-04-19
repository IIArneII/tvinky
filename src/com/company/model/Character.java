package com.company.model;

public class Character {

    private double posCharacterX;
    private double posCharacterY;
    private double angCharacter;
    private double speed;
    private double health;

    public Character(){
        this.posCharacterX = 5;
        this.posCharacterY = 5;
        this.angCharacter = 0;
        this.health = 100;
        this.speed = 0;
    }

    public double getPosCharacterX(){return this.posCharacterX;}
    public double getPosCharacterY(){return this.posCharacterY;}
    public double getAngCharacter(){return this.angCharacter;}
    public double getHealth(){return this.health;}
    public double getSpeed(){return this.speed;}
    public void setPosCharacterXRedefinition(double n){this.posCharacterX = n;}
    public void setPosCharacterYRedefinition(double n){this.posCharacterY = n;}
    public void setPosCharacterXPlus(double n){this.posCharacterX += n;}
    public void setPosCharacterYPlus(double n){this.posCharacterY += n;}
    public void setPosCharacterXMinus(double n){this.posCharacterX -= n;}
    public void setPosCharacterYMinus(double n){this.posCharacterY -= n;}
    public void setAngCharacterPlus(double n){this.angCharacter += n;}
    public void setAngCharacterMinus(double n){this.angCharacter -= n;}
    public void setHealthPlus(double n){this.health += n;}
    public void setHealthMinus(double n){this.health -= n;}

}
