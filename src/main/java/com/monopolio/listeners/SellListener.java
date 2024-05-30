package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.board.boxes.City;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SoundManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Gestisce il click del bottone che permette di vendere oggetti (case e città).
 */
public class SellListener implements EventHandler<ActionEvent> {
    private Game game;
    private GameManager gameManager;

    public SellListener(Game game, GameManager gameManager) {
        this.gameManager = gameManager;
        this.game = game;
    }

    /**
     * Se viene selezionata una proprietà e tutte le condizioni sono verificate permette di vendere.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if(game.getSelectedButton() == null) {
            AlertManager.showError("Non ci sono proprietà selezionate");
        } else {
            game.refreshPlayersGUI();
            if(gameManager.getCity(game.getSelectedButtonIndex()) instanceof City && ((City) gameManager.getCity(game.getSelectedButtonIndex())).getHouseNumber() >= 1) {
                gameManager.sellHouse(gameManager.getCity(game.getSelectedButtonIndex()));
            } else {
                gameManager.sellPropety(gameManager.getCity(game.getSelectedButtonIndex()));
            }
        }
    }
}
