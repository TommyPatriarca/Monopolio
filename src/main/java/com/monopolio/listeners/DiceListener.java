package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.InterfaceManager;
import com.monopolio.managers.SoundManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class DiceListener implements EventHandler<ActionEvent> {
    private DiceButton dice;
    private Game game;
    private GameManager gameManager;

    public DiceListener(DiceButton dice, GameManager gameManager, Game game) {
        this.dice = dice;
        this.gameManager = gameManager;
        this.game = game;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        // Logica per la gestione dei dadi
        SoundManager.dices();
        int rolled = dice.roll();
        if(Monopolio.isDevMode()) {
            System.out.println("the number (" + rolled + ") was rolled");
        }

        dice.colorRed();
        dice.disable();

        // Logica per la gestione della nuova posizione del player
        if(gameManager.areDicesRolled()) {
            game.getLogManager().log(gameManager.getCurrentPlayer().getName()+" ha ottentuo un "+gameManager.getDicesRoll()+" dai dadi");
            if(gameManager.getCurrentPlayer().inPrison()) {
                // se il player è in prison
                if(gameManager.getCurrentPlayer().getTurnsInPrison() == 2) {
                    // 3 turni fatti (1 è quello di uscita), esci di prigione
                    game.getLogManager().log(gameManager.getCurrentPlayer().getName() + " è ora uscito di prigione dopo 3 turni");
                    gameManager.getCurrentPlayer().setInPrison(false);
                    gameManager.getCurrentPlayer().setTurnsInPrison(0);
                } else if(gameManager.isDoubleDices()) {
                    // dadi doppi tirati, esci di prigione
                    game.getLogManager().log(gameManager.getCurrentPlayer().getName() + " è ora uscito di prigione per aver tirato un doppio dado");
                    gameManager.getCurrentPlayer().setInPrison(false);
                    gameManager.getCurrentPlayer().setTurnsInPrison(0);
                } else {
                    // non uscire di prigione
                    game.getLogManager().log(gameManager.getCurrentPlayer().getName() + " rimane in prigione");
                    gameManager.getCurrentPlayer().setTurnsInPrison(gameManager.getCurrentPlayer().getTurnsInPrison()+1);
                }
            } else {
                gameManager.getCurrentPlayer().moveForward(gameManager.getDicesRoll());
                gameManager.handleMovement();
                if(Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI) {
                    // Togli la pedina precedente
                    game.removePlayerIcons(gameManager.getCurrentPlayer(), game.getCell(gameManager.getCurrentPlayer().getOldPosition()));

                    System.out.println("Pawn should be moved to ("+gameManager.getCurrentPlayer().getPosition()+")");
                    ImageView[] playerIcons = game.createPlayerIcons(gameManager.getCurrentPlayer().getPosition());
                    for (ImageView icon : playerIcons) {
                        game.getCell(gameManager.getCurrentPlayer().getPosition()).getChildren().add(icon);
                    }
                }
            }
        }

        // Alla fine del round -> dice.setDefault();
    }
}
