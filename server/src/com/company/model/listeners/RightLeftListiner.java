package com.company.model.listeners;/*package com.company.model.listeners;

import com.company.model.math.Angles;

public class RightLeftListiner extends Thread{
    Movement movement;

    public RightLeftListiner(Movement movement){
        this.movement = movement;
    }

    @Override
    public void run() {
        while (movement.isMovementLaunched()){
            if(!movement.isMovementOnPause()){
                try { Thread.currentThread().sleep(1); } catch (Exception e) {}
                if(movement.client.LEFT == 1) {
                    double posCharacterXPrev = movement.client.gameClient.getEntityDynamicList().get(0).getX();
                    double posCharacterYPrev = movement.client.gameClient.getEntityDynamicList().get(0).getY();
                    movement.client.gameClient.getEntityDynamicList().get(0).setX(movement.client.gameClient.getEntityDynamicList().get(0).getX() - Math.cos(Angles.converteDegreeToRadian(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter() - Angles.Ang90)) /360);
                    movement.client.gameClient.getEntityDynamicList().get(0).setY(movement.client.gameClient.getEntityDynamicList().get(0).getY() - Math.sin(Angles.converteDegreeToRadian(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter() - Angles.Ang90)) /360);
                    if(movement.client.gameClient.getMap().isWall(movement.client.gameClient.getEntityDynamicList().get(0).getX(), movement.client.gameClient.getEntityDynamicList().get(0).getY()) > 0){
                        movement.client.gameClient.getEntityDynamicList().get(0).setX(posCharacterXPrev);
                        movement.client.gameClient.getEntityDynamicList().get(0).setY(posCharacterYPrev);
                    }
                }
                if (movement.client.RIGHT == 1){
                    double posCharacterXPrev = movement.client.gameClient.getEntityDynamicList().get(0).getX();
                    double posCharacterYPrev = movement.client.gameClient.getEntityDynamicList().get(0).getY();
                    movement.client.gameClient.getEntityDynamicList().get(0).setX(movement.client.gameClient.getEntityDynamicList().get(0).getX() + Math.cos(Angles.converteDegreeToRadian(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter()  - Angles.Ang90)) /360);
                    movement.client.gameClient.getEntityDynamicList().get(0).setY(movement.client.gameClient.getEntityDynamicList().get(0).getY() + Math.sin(Angles.converteDegreeToRadian(movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter() - Angles.Ang90)) /360);
                    if(movement.client.gameClient.getMap().isWall(movement.client.gameClient.getEntityDynamicList().get(0).getX(), movement.client.gameClient.getEntityDynamicList().get(0).getY()) > 0){
                        movement.client.gameClient.getEntityDynamicList().get(0).setX(posCharacterXPrev);
                        movement.client.gameClient.getEntityDynamicList().get(0).setY(posCharacterYPrev);
                    }

                }
            }
            else try { Thread.currentThread().sleep(10);
                System.out.println("Птокок вправо влево на паузе");} catch (Exception e) {}
        }
        System.out.println("Поток влево вправо завершился");
    }
}

*/