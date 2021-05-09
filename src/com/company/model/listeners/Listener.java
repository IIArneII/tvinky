package com.company.model.listeners;

import com.company.model.math.Angles;

public class BackForthListener extends Thread{
    private Event event;
    private Realize realize;
    private int value;
    private boolean launched;
    private boolean pause;

    public BackForthListener(Event event, int value, Realize realize){
        this.event = event;
        this.realize = realize;
        this.value = value;
        pause = false;
        launched = false;
    }

    @Override
    public void run() {
        while (launched){
            if(!pause) {
                if(event.getEvent() == value) {
                    realize.make();
                }
                /*
                try { Thread.currentThread().sleep(1); } catch (Exception e) {}
                if(movement.client.UP == 1){
                    double posCharacterXPrev = movement.client.gameClient.getEntityDynamicList().get(0).getX();
                    double posCharacterYPrev = movement.client.gameClient.getEntityDynamicList().get(0).getY();
                    movement.client.gameClient.getEntityDynamicList().get(0).setX(movement.client.gameClient.getEntityDynamicList().get(0).getX() + Math.cos(Angles.converteDegreeToRadian(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter())) /360);
                    movement.client.gameClient.getEntityDynamicList().get(0).setY(movement.client.gameClient.getEntityDynamicList().get(0).getY() + Math.sin(Angles.converteDegreeToRadian(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter())) /360);
                    if(movement.client.gameClient.getMap().isWall(movement.client.gameClient.getEntityDynamicList().get(0).getX(), movement.client.gameClient.getEntityDynamicList().get(0).getY()) > 0){
                        movement.client.gameClient.getEntityDynamicList().get(0).setX(posCharacterXPrev);
                        movement.client.gameClient.getEntityDynamicList().get(0).setY(posCharacterYPrev);
                    }
                }
                if(movement.client.DOWN == 1){
                    double posCharacterXPrev = movement.client.gameClient.getEntityDynamicList().get(0).getX();
                    double posCharacterYPrev = movement.client.gameClient.getEntityDynamicList().get(0).getY();
                    movement.client.gameClient.getEntityDynamicList().get(0).setX(movement.client.gameClient.getEntityDynamicList().get(0).getX() - Math.cos(Angles.converteDegreeToRadian(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter())) /360);
                    movement.client.gameClient.getEntityDynamicList().get(0).setY(movement.client.gameClient.getEntityDynamicList().get(0).getY() - Math.sin(Angles.converteDegreeToRadian(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter())) /360);
                    if(movement.client.gameClient.getMap().isWall(movement.client.gameClient.getEntityDynamicList().get(0).getX(), movement.client.gameClient.getEntityDynamicList().get(0).getY()) > 0){
                        movement.client.gameClient.getEntityDynamicList().get(0).setX(posCharacterXPrev);
                        movement.client.gameClient.getEntityDynamicList().get(0).setY(posCharacterYPrev);
                    }
                }*/
            }
        }
    }
}
