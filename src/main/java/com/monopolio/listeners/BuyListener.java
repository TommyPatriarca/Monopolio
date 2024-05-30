package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SoundManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Gestisce i click per comprare gli oggetti (città,case).
 */
public class BuyListener implements EventHandler<ActionEvent> {
    private GameManager gameManager;
    private Game game;

    public BuyListener(GameManager gameManager, Game game) {
        this.gameManager = gameManager;
        this.game = game;
    }

    /**
     * Permette di compare le cose solamente se sono stati tirati i dadi.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if(gameManager.areDicesRolled()) {
            if(game.getSelectedButton() != null) {
                gameManager.buyHouse(gameManager.getCity(game.getSelectedButtonIndex()));
            } else {
                gameManager.buyPropety();
                game.refreshPlayersGUI();
            }
        } else {
            AlertManager.showError("Non hai ancora finito di tirare i dadi!");
        }
    }
}
