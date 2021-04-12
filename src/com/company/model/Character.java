package com.company.model;

public class Character {

    private double posCharacterX;
    private double posCharacterY;
    private double angCharacter;
    private double speed;

    public Character(){
        this.posCharacterX = 5;
        this.posCharacterY = 5;
        this.angCharacter = 0;
        this.speed = 0;
    }

    public double getPosCharacterX(){return this.posCharacterX;}
    public double getPosCharacterY(){return this.posCharacterY;}
    public double getAngCharacter(){return this.angCharacter;}
    public double getSpeed(){return this.speed;}
    public void PosCharacterXRedefinition(double n){this.posCharacterX = n;}
    public void PosCharacterYRedefinition(double n){this.posCharacterY = n;}
    public void PosCharacterXPlus(double n){this.posCharacterX += n;}
    public void PosCharacterYPlus(double n){this.posCharacterY += n;}
    public void PosCharacterXMinus(double n){this.posCharacterX -= n;}
    public void PosCharacterYMinus(double n){this.posCharacterY -= n;}
    public void AngCharacterPlus(double n){this.angCharacter += n;}
    public void AngCharacterMinus(double n){this.angCharacter -= n;}

}
