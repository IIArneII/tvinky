package com.company.model;

public class Rendering extends Thread {


    public static final int Width = 800;
    public static final int Height = 600;
    public static final int Width_Center = Width / 2;
    public static final int Height_Center = Height / 2;


    private GameClient gameClient;
    private Screen screen;

    public Rendering(GameClient gameClient) {
        this.screen = new Screen();
        this.gameClient = gameClient;
    }

    public Screen getScreen() {
        return this.screen;
    }

    @Override
    public void run() {
        double radX, radY, stepX, stepY, angRad;

        while (true) {

            try { Thread.currentThread().sleep(1); } catch (Exception e) {}
            int[][] temp = new int[800][5];

            angRad = (gameClient.getEntityDynamicList().get(0).getAngCharacter() - Angles.Ang30);

            for (int rad = 0; rad < Angles.Ang60; rad++) {
                radX = gameClient.getEntityDynamicList().get(0).getX();
                radY = gameClient.getEntityDynamicList().get(0).getY();
                stepX = Math.cos(Angles.converteDegreeToRadian(angRad)) / 80;
                stepY = Math.sin(Angles.converteDegreeToRadian(angRad)) / 80;

                int wall = 0;
                int distant = 1;
                while (wall == 0) {
                    radX += stepX;
                    radY += stepY;
                    //wall = this.gameClient.getMap().getMap()[(int) radX][(int) radY];
                    wall = this.gameClient.getMap().isWall(radX, radY);
                    distant++;

                }
                int heightWall = (int)(25000 /(distant*Math.cos(Angles.converteDegreeToRadian(angRad)  - Angles.converteDegreeToRadian(gameClient.getEntityDynamicList().get(0).getAngCharacter()))));

                temp[rad][0] = rad;
                temp[rad][1] = Height_Center - heightWall;
                temp[rad][2] = Height_Center + heightWall;
                temp[rad][3] = Height;
                temp[rad][4] = wall - 1;

                angRad++;
            }
            this.screen.setScreen(temp);
        }

    }
}
