package com.monopolio;

import com.monopolio.managers.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Monopolio extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.showModeScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
