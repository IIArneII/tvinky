package com.company.model;

import com.company.model.map.WallPoint;

public class Rendering extends Thread {

    public static final int Width = 800;
    public static final int Height = 600;
    public static final int Width_Center = Width / 2;
    public static final int Height_Center = Height / 2;

    private GameClient gameClient;
    private Screen screen;
    private boolean renderingLaunched;
    private boolean renderingOnPause;
    private int renderingMethod;

    public Rendering(GameClient gameClient) {
        this.screen = new Screen();
        this.gameClient = gameClient;
        renderingLaunched = false;
        renderingOnPause = false;
        renderingMethod = 0;
    }

    public boolean isRenderingLaunched() {
        return renderingLaunched;
    }

    public void setRenderingLaunched(boolean renderingLaunched) {
        this.renderingLaunched = renderingLaunched;
    }

    public void setRenderingOnPause(boolean renderingOnPause) {
        this.renderingOnPause = renderingOnPause;
    }

    public boolean isRenderingOnPause(){
        return renderingOnPause;
    }

    public int getRenderingMethod() {
        return renderingMethod;
    }

    public void setRenderingMethod(int renderingMethod) {
        this.renderingMethod = renderingMethod;
    }

    public Screen getScreen() {
        return this.screen;
    }

    @Override
    public void run() {
        renderingLaunched = true;
        if(renderingMethod == 0){
            double radX, radY, stepX, stepY, angRad;
            while (renderingLaunched) {
                if(!renderingOnPause){
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
                else try { Thread.currentThread().sleep(10); } catch (Exception e) {}
            }
        }
        else if(renderingMethod == 1){
            double radX, radY, stepX, stepY, angRad;
            while (renderingLaunched) {
                if(!renderingOnPause){
                    try { Thread.currentThread().sleep(1); } catch (Exception e) {}
                    int[][] temp = new int[800][5];

                    angRad = (gameClient.getEntityDynamicList().get(0).getAngCharacter() - Angles.Ang30);

                    for (int rad = 0; rad < Angles.Ang60; rad++) {
                        radX = gameClient.getEntityDynamicList().get(0).getX();
                        radY = gameClient.getEntityDynamicList().get(0).getY();

/*
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
                System.out.println(distant);
                int heightWall = (int)(25000 /(distant*Math.cos(Angles.converteDegreeToRadian(angRad)  - Angles.converteDegreeToRadian(gameClient.getEntityDynamicList().get(0).getAngCharacter()))));

 */
                        WallPoint distant = this.gameClient.getMap().distance(new Section(radX, radY, radX + Math.cos(Angles.converteDegreeToRadian(gameClient.getEntityDynamicList().get(0).getAngCharacter() - Angles.Ang30 + rad)), radY + Math.sin(Angles.converteDegreeToRadian(gameClient.getEntityDynamicList().get(0).getAngCharacter() - Angles.Ang30 + rad))), rad);

                        //int heightWall = (int)(2500 /(distant*Math.cos(Angles.converteDegreeToRadian(angRad)  - Angles.converteDegreeToRadian(gameClient.getEntityDynamicList().get(0).getAngCharacter()))));

                        int heightWall = (int)(2500 / (distant.getDistance() * 10 *  Math.cos(Angles.converteDegreeToRadian(angRad) - Angles.converteDegreeToRadian(gameClient.getEntityDynamicList().get(0).getAngCharacter()))));

                        temp[rad][0] = rad;
                        temp[rad][1] = Height_Center - heightWall;
                        temp[rad][2] = Height_Center + heightWall;
                        temp[rad][3] = Height;
                        temp[rad][4] = distant.getColor();

                        angRad++;

                        //renderingAdapter.drowLine(rad, Height_Center - heightWall, Height_Center + heightWall, Height, wall - 1);
                    }
                    this.screen.setScreen(temp);
                }
                else try { Thread.currentThread().sleep(10); } catch (Exception e) {}
            }
        }
        System.out.println("Потоко рендеринг завершился");
    }
}
