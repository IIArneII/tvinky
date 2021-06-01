package com.company.model.listeners;

public class Listener extends Thread{
    private Event event;
    private Realize realize;
    private boolean launched;
    private boolean pause;

    public Listener(Event event, Realize realize){
        this.event = event;
        this.realize = realize;
        pause = false;
        launched = false;
    }

    @Override
    public void run() {
        launched = true;
        while (launched){
            if(!pause) {
                try { Thread.currentThread().sleep(1); } catch (Exception e) {}
                if(event.isEvent()) {
                    //System.out.println(event.isEvent() + "------");
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

    public boolean isLaunched() {
        return launched;
    }

    public boolean isPause() {
        return pause;
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
