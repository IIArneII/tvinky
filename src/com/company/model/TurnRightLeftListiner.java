package com.company.model;

public class TurnRightLeftListiner extends Thread {
    Movement movement;

    public TurnRightLeftListiner(Movement movement){
        this.movement = movement;
    }

    @Override
    public void run() {
        while (true){
            try { Thread.currentThread().sleep(1); } catch (Exception e) {}
            if(movement.client.TurnLEFT == 1){
                movement.client.gameClient.getEntityDynamicList().get(0).setAngCharacter(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter() - 0.1 * (Angles.Ang6/8));
            }
            if(movement.client.TurnRIGHT == 1){
                movement.client.gameClient.getEntityDynamicList().get(0).setAngCharacter(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter() + 0.1 * (Angles.Ang6/8));
            }
        }
    }
}
