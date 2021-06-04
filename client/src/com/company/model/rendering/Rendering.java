package com.company.model.rendering;

import com.company.model.client.Client;
import com.company.model.entity.Character;
import com.company.model.map.Map;
import com.company.model.map.Wall;
import com.company.model.map.WallPoint;
import com.company.model.math.Angles;
import com.company.model.math.RayCasting;
import com.company.model.math.Section;
import com.company.model.game.Game;

import java.util.ArrayList;

public class Rendering extends Thread {

    public static final int Width = 800;
    public static final int Height = 600;
    public static final int Width_Center = Width / 2;
    public static final int Height_Center = Height / 2;

    private Character character;
    private Game game;
    private Screen screen;
    private boolean renderingLaunched;
    private boolean renderingOnPause;

    public Rendering(Character character, Game game) {
        this.screen = new Screen();
        this.character = character;
        this.game = game;
        renderingLaunched = false;
        renderingOnPause = false;
    }

    public boolean isRenderingLaunched() {
        return renderingLaunched;
    }

    public void setLaunched(boolean renderingLaunched) {
        this.renderingLaunched = renderingLaunched;
    }

    public void setRenderingOnPause(boolean renderingOnPause) {
        this.renderingOnPause = renderingOnPause;
    }

    public boolean isRenderingOnPause(){
        return renderingOnPause;
    }

    public Screen getScreen() {
        return this.screen;
    }

    @Override
    public void run() {
        renderingLaunched = true;
        double radX, radY, stepX, stepY, angRad;
        while (renderingLaunched) {
            if(!renderingOnPause){
                try { Thread.currentThread().sleep(1); } catch (Exception e) {}

                int[][] temp = new int[800][6];
                double[] width = new double[800];

                angRad = (character.getAngCharacter() - Angles.Ang30);

                for (int rad = 0; rad < Angles.Ang60; rad++) {
                    radX = character.getX();
                    radY = character.getY();

                    WallPoint distant = RayCasting.rayCasting(new Section(radX, radY, radX + Math.cos(Angles.converteDegreeToRadian(character.getAngCharacter() + Angles.Ang30 - rad)), radY + Math.sin(Angles.converteDegreeToRadian(character.getAngCharacter() + Angles.Ang30 - rad))), game.getMap().getWalls());

                    int heightWall = (int)(250 / (distant.getDistance() * Math.cos(Angles.converteDegreeToRadian(angRad) - Angles.converteDegreeToRadian(character.getAngCharacter()))));

                    temp[rad][0] = rad;
                    temp[rad][1] = Height_Center - heightWall;
                    temp[rad][2] = Height_Center + heightWall;
                    temp[rad][3] = Height;
                    temp[rad][4] = distant.getColor();
                    temp[rad][5] = distant.getTextureID();
                    width[rad] = distant.getTextureK();

                    angRad++;
                }
                this.screen.setScreen(temp, width);
            }
            else try { Thread.currentThread().sleep(10);} catch (Exception e) {}
        }
        System.out.println("Поток отрисовки в модели завершился");
    }
}
