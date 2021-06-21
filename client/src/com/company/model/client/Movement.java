package com.company.model.client;

import com.company.model.Message;
import com.company.model.game.Shot;
import com.company.model.listeners.Event;
import com.company.model.listeners.Listener;
import com.company.model.listeners.Realize;
import com.company.model.map.*;
import com.company.model.math.Angles;
import com.company.model.game.Character;
import com.company.model.math.Point;
import com.company.model.math.RayCasting;
import com.company.model.math.Section;
import com.company.model.game.Game;

public class Movement {
    private Character character;
    private Map map;
    private Game game;
    private Connection connection;

    private Listener backForthListener;
    private Listener rightLeftListiner;
    private Listener turnRightLeftListiner;
    private Listener shotListener;

    private Event backForthEvent;
    private Event rightLeftEvent;
    private Event turnRightLeftEvent;
    private Event shotEvent;

    private boolean launched;
    private boolean pause;

    private boolean back;
    private boolean forth;
    private boolean right;
    private boolean left;
    private boolean turnRight;
    private boolean turnLeft;
    private boolean shot;

    public Movement(Character character, Game game, Connection connection){
        back = false;
        forth = false;
        right = false;
        left = false;
        turnLeft = false;
        turnRight = false;
        shot = false;

        this.character = character;
        this.map = game.getMap();
        this.game = game;
        this.connection = connection;

        backForthEvent = new Event();
        rightLeftEvent = new Event();
        turnRightLeftEvent = new Event();
        shotEvent = new Event();

        launched = false;
        pause = false;

        this.backForthListener = new Listener(backForthEvent, new Realize() {
            @Override
            public void make() {
                backForth();
            }
        });

        this.rightLeftListiner = new Listener(rightLeftEvent, new Realize() {
            @Override
            public void make() {
                rightLeft();
            }
        });

        this.turnRightLeftListiner = new Listener(turnRightLeftEvent, new Realize() {
            @Override
            public void make() {
                turnRightLeft();
            }
        });

        this.shotListener = new Listener(shotEvent, new Realize() {
            @Override
            public void make() {
                shot();
            }
        });
    }

    public void backForth(){
        if(forth){
            double posCharacterXPrev = character.getX();
            double posCharacterYPrev = character.getY();
            character.setX(character.getX() + Math.cos(Angles.convert(character.getAng())) /360);
            character.setY(character.getY() + Math.sin(Angles.convert(character.getAng())) /360);
            if(map.isWall(character.getX(), character.getY()) > 0){
                character.setX(posCharacterXPrev);
                character.setY(posCharacterYPrev);
            }
        }
        if(back){
            double posCharacterXPrev = character.getX();
            double posCharacterYPrev = character.getY();
            character.setX(character.getX() - Math.cos(Angles.convert(character.getAng())) /360);
            character.setY(character.getY() - Math.sin(Angles.convert(character.getAng())) /360);
            if(map.isWall(character.getX(), character.getY()) > 0){
                character.setX(posCharacterXPrev);
                character.setY(posCharacterYPrev);
            }
        }
    }

    public void rightLeft(){
        if(left){
            double posCharacterXPrev = character.getX();
            double posCharacterYPrev = character.getY();
            character.setX(character.getX() - Math.cos(Angles.convert(character.getAng() - Angles.Ang90)) /360);
            character.setY(character.getY() - Math.sin(Angles.convert(character.getAng() - Angles.Ang90)) /360);
            if(map.isWall(character.getX(), character.getY()) > 0){
                character.setX(posCharacterXPrev);
                character.setY(posCharacterYPrev);
            }
        }
        if(right){
            double posCharacterXPrev = character.getX();
            double posCharacterYPrev = character.getY();
            character.setX(character.getX() + Math.cos(Angles.convert(character.getAng()  - Angles.Ang90)) /360);
            character.setY(character.getY() + Math.sin(Angles.convert(character.getAng() - Angles.Ang90)) /360);
            if(map.isWall(character.getX(), character.getY()) > 0){
                character.setX(posCharacterXPrev);
                character.setY(posCharacterYPrev);
            }
        }
    }

    public void turnRightLeft(){
        if(turnLeft){
            character.setAng(character.getAng() + 0.3 * (Angles.Ang6/8));
        }
        if(turnRight){
            character.setAng(character.getAng() - 0.3 * (Angles.Ang6/8));
        }
    }

    public void shot(){
        try {
            Section section = new Section(character.getX(), character.getY(),
                    character.getX() + Math.cos(Angles.convert(character.getAng())),
                    character.getY() + Math.sin(Angles.convert(character.getAng())));
            if(connection != null) connection.write(new Message("shot", new Shot(section, character)));
        }
        catch (Exception e){
            System.out.println("Ошибка при отправке выстрела на сервер: " + e.getMessage());
        }
        finally {
            try {
                Thread.currentThread().sleep(1000);
            }
            catch (Exception e){}
        }
    }

    public boolean isLaunched() {
        return launched;
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    public void stop(){
        this.launched = false;
        backForthListener.setLaunched(false);
        rightLeftListiner.setLaunched(false);
        turnRightLeftListiner.setLaunched(false);
        shotListener.setLaunched(false);
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
        backForthListener.setPause(pause);
        rightLeftListiner.setPause(pause);
        turnRightLeftListiner.setPause(pause);
        shotListener.setPause(pause);
    }

    public void setBackForthEvent(boolean event){
        backForthEvent.setEvent(event);
    }

    public void setRightLeftEvent(boolean event){
        rightLeftEvent.setEvent(event);
    }

    public void setTurnRightLeftEvent(boolean event){
        turnRightLeftEvent.setEvent(event);
    }

    public void setShotEvent(boolean event){
        shotEvent.setEvent(event);
    }

    public void setBack(boolean back) {
        this.back = back;
    }

    public void setForth(boolean forth) {
        this.forth = forth;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setTurnLeft(boolean turnLeft) {
        this.turnLeft = turnLeft;
    }

    public void setTurnRight(boolean turnRight) {
        this.turnRight = turnRight;
    }

    public void start(){
        launched = true;
        backForthListener.start();
        rightLeftListiner.start();
        turnRightLeftListiner.start();
        shotListener.start();
    }
}
