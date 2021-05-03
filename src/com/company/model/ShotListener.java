package com.company.model;

public class ShotListener extends Thread {
    Movement movement;

    public ShotListener(Movement movement) {
        this.movement = movement;
    }

    @Override
    public void run() {
        while (true) {
            if (movement.client.SHOOT == 1) {
                double distant = 0;
                boolean hitWall = false;

                double angRad = (movement.client.gameClient.getEntityDynamicList().get(0).getAngCharacter() - Angles.Ang30 / 6);

                double stepX = Math.cos(Angles.converteDegreeToRadian(angRad)) / 80;
                double stepY = Math.sin(Angles.converteDegreeToRadian(angRad)) / 80;

                while (!hitWall) {
                    distant += 1;

                    double radX = movement.client.gameClient.getEntityDynamicList().get(0).getX() + stepX * distant;
                    double radY = movement.client.gameClient.getEntityDynamicList().get(0).getY() + stepY * distant;

                    if (movement.client.gameClient.getMap().getMap()[(int) radX][(int) radY] != 0) {
                        hitWall = true;
                        System.out.print("Hitwall. coordinates:" + (int) radX + " " + (int) radY);
                        //this.movement.client.gameClient.getMap().setMap((int)radX, (int)radY, 0);
                    }
                }
            }
        }
    }
}
