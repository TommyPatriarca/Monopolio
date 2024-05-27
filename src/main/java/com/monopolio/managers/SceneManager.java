package com.monopolio.managers;

import com.monopolio.ui.EndGame;
import com.monopolio.ui.Game;
import com.monopolio.ui.Mode;
import com.monopolio.ui.Start;
import javafx.stage.Stage;

/**
 * Definisce quale schermata del gioco deve essere avviata.
 */
public class SceneManager {
    private Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    /**
     * Apre la schermata in cui si ha la possibilità di scegliere la modalità d'avvio della partita.
     */
    public void showModeScreen() {
        Mode mode = new Mode(this);
        mode.start(stage);
    }

    /**
     * Apre la schermata in cui si ha la possibilità di scegliere il nickname.
     * @param settings è la schermata dalla quale vengono prese le impostazioni.
     */
    public void showStartScreen(Stage settings) {
        Start start = new Start(this);
        applySettings(settings);
        start.start(stage);
    }

    /**
     * Apre la schermata del gioco completo.
     * @param settings è la schermata dalla quale vengono prese le impostazioni.
     * @param playerNames è l'array contenente i nomi dei giocatori.
     */
    public void showGameScreen(Stage settings, String[] playerNames) {
        Game game = new Game(playerNames);
        applySettings(settings);
        game.start(stage);
    }

    /**
     * Applica le impostazioni di una schermata ad una schermata.
     * @param settings è la schermata dalla quale vengono prese le impostazioni.
     */
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
