package com.company.model;
import javafx.application.Platform;

import com.company.view.game.GameController;

public class RenderingAdapter implements RenderingAdapterInterface {
    private GameController gameController;

    public RenderingAdapter(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void drawLines(Screen screen) {
        gameController.drawLines(screen);
    }

    @Override
    public void drowLine(int rad, int r1, int r2, int r3, int r4) {
        gameController.drawLine(rad, r1, r2, r3, r4);

    }
}
