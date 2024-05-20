package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.managers.GameManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class BoxListener implements EventHandler<ActionEvent> {
    private Game game;
    private Button button;
    private int index;

    public BoxListener(Game game, Button button, int index) {
        this.game = game;
        this.button = button;
        this.index = index;
    }

    @Override
    public void handle(ActionEvent event) {
        if(game.getSelectedButton() != null && game.getSelectedButton() == button) {
            // deselect
            button.setStyle("-fx-cursor: hand; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
            game.setSelectedButtonIndex(0);
            game.setSelectedButton(null);
        } else if(game.getSelectedButton() != null && game.getSelectedButton() != button) {
            // deselect and select
            game.getSelectedButton().setStyle("-fx-cursor: hand; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
            button.setStyle("-fx-cursor: hand; -fx-border-color: red; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
            game.setSelectedButtonIndex(index);
            game.setSelectedButton(button);
        } else {
            // select
            button.setStyle("-fx-cursor: hand; -fx-border-color: red; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
            game.setSelectedButtonIndex(index);
            game.setSelectedButton(button);
        }
        if(Monopolio.isDevMode()) {
            System.out.println("the button (" + button.getText() + ") got clicked");
        }
    }
}
