package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Gestisce il click del bottone per cambiare turno.
 */
public class TurnListener implements EventHandler<ActionEvent> {
    private Game game;
    private GameManager gameManager;

    public TurnListener(GameManager gameManager, Game game) {
        this.gameManager = gameManager;
        this.game = game;
    }

    /**
     * Controlla che tutte le condizioni siano verificate e se lo sono cambia il turno.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        if(!gameManager.areDicesRolled()) {
            // the player cannot end turn if dices are not rolled.
            AlertManager.showError("Non puoi terminare il turno prima di aver tirato entrambi i dadi.");
            return;
        }

        if(gameManager.getCurrentPlayer().getMoney() < 0) {
            // the player cannot end turn if dices are not rolled.
            AlertManager.showError("Non puoi terminare il turno con il saldo in negativo");
            return;
        }

        for(int i=0; i<4; i++) {
            if(gameManager.getPlayer(i).isMyTurn()) {
                gameManager.getPlayer(i).setMyTurn(false);
                if(i==3 || gameManager.getPlayer(i+1).getName().isEmpty()) {
                    if(gameManager.getPlayer(0).getName().isEmpty()) {
                        if(gameManager.getPlayer(1).getName().isEmpty()) {
                            if(!gameManager.getPlayer(2).getName().isEmpty()) {
                                gameManager.getPlayer(2).setMyTurn(true);
                            }
                        } else {
                            gameManager.getPlayer(1).setMyTurn(true);
                        }
                    } else {
                        gameManager.getPlayer(0).setMyTurn(true);
                    }
                } else {
                    gameManager.getPlayer(i+1).setMyTurn(true);
                } // logic to end the player's turn.
                if(Monopolio.isDevMode()) {
                    System.out.println(gameManager.getPlayer(i).getName() + " (Player) ended the turn");
                }

                // Restore the dices for the next player.
                gameManager.restoreDices();

                break;
            }
        }

        game.getLogManager().setMainLog("E' il turno del giocatore: " + gameManager.getCurrentPlayer().getName());
    }
}
