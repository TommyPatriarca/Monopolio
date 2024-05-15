package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.board.buttons.DiceButton;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.InterfaceManager;
import com.monopolio.managers.SoundManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        // Logica per la gestione del player
        gameManager.getCurrentPlayer().moveForward(rolled);
        if(Monopolio.isDevMode()) {
            System.out.println(gameManager.getCurrentPlayer().getName() + " (Player) was moved to the position (" + gameManager.getCurrentPlayer().getPosition() + ")");
        }

        // Logica per la gestione della nuova posizione del player
        if(gameManager.areDicesRolled()) {
            gameManager.handleMovement();
            if(Monopolio.getInterfaceType() == InterfaceManager.InterfaceType.GUI) {
                // Togli la pedina precedente
                System.out.println("Pawn should be moved to ("+gameManager.getCurrentPlayer().getPosition()+")");
                ImageView[] playerIcons = game.createPlayerIcons(gameManager.getCurrentPlayer().getPosition(), 100);
                for (ImageView icon : playerIcons) {
                    game.getCell(gameManager.getCurrentPlayer().getPosition()).getChildren().add(icon);
                }
            }
        }

        // Alla fine del round -> dice.setDefault();
    }
}
