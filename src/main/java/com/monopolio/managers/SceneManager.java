package com.monopolio.managers;

import com.monopolio.ui.Game;
import com.monopolio.ui.Mode;
import com.monopolio.ui.Start;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void showModeScreen() {
        Mode mode = new Mode(this);
        mode.start(stage);
    }

    public void showStartScreen(Stage settings) {
        Start start = new Start(this);
        applySettings(settings);
        start.start(stage);
    }

    public void showGameScreen(Stage settings, String[] playerNames) {
        Game game = new Game(playerNames);
        applySettings(settings);
        game.start(stage);
    }

    private void applySettings(Stage settings) {
        stage.setFullScreen(settings.isFullScreen());
        stage.setFullScreenExitHint(settings.getFullScreenExitHint());
        stage.setFullScreenExitKeyCombination(settings.getFullScreenExitKeyCombination());
        stage.setMaximized(settings.isMaximized());
        stage.setWidth(settings.getWidth());
        stage.setHeight(settings.getHeight());
        stage.setX(settings.getX());
        stage.setY(settings.getY());
    }
}
