package com.company.model.listeners;/*package com.company.model.listeners;

import com.company.model.math.Angles;

public class TurnRightLeftListiner extends Thread {
    Movement movement;

    public TurnRightLeftListiner(Movement movement){
        this.movement = movement;
    }

    @Override
    public void run() {
        while (movement.isMovementLaunched()){
            if(!movement.isMovementOnPause()){
                try { Thread.currentThread().sleep(1); } catch (Exception e) {}
                if(movement.client.TurnLEFT == 1){
                    movement.client.gameClient.getEntityDynamicList().get(0).setAngCharacter(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter() + 0.1 * (Angles.Ang6/8));
                }
                if(movement.client.TurnRIGHT == 1){
                    movement.client.gameClient.getEntityDynamicList().get(0).setAngCharacter(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter() - 0.1 * (Angles.Ang6/8));
                }
            }
            else try { Thread.currentThread().sleep(10); } catch (Exception e) {}
        }
        System.out.println("Поток поворота завершился");
    }
}
*/