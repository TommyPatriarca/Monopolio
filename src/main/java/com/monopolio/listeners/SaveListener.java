package com.monopolio.listeners;

import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SceneManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Gestisce il click per il salvataggio della partita.
 */
public class SaveListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;

    public SaveListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Dopo aver cliccato il pulsante salva su file serializzabile la partita.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if(!AlertManager.showDialog("Partita salvata con successo")) return;
        gameManager.saveGame();
    }
}
