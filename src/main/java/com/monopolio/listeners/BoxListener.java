package com.monopolio.listeners;

import com.monopolio.Monopolio;
import com.monopolio.board.boxes.City;
import com.monopolio.board.buttons.BuyButton;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class BoxListener implements EventHandler<ActionEvent> {
    private BuyButton buyButton;
    private GameManager gameManager;
    private Game game;
    private Button button;
    private int index;

    public BoxListener(BuyButton buyButton, GameManager gameManager, Game game, Button button, int index) {
        this.gameManager = gameManager;
        this.buyButton = buyButton;
        this.game = game;
        this.button = button;
        this.index = index;
    }

    @Override
    public void handle(ActionEvent event) {
        if(game.getSelectedButton() != null && game.getSelectedButton() == button) {
            buyButton.setText("Compra");
            // deselect
            String style;
            switch (gameManager.getPropertyOwnerIndex(game.getSelectedButtonIndex())) {
                case 0:
                    style = "-fx-cursor: hand; -fx-border-color: green; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;";
                    break;
                case 1:
                    style = "-fx-cursor: hand; -fx-border-color: yellow; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;";
                    break;
                case 2:
                    style = "-fx-cursor: hand; -fx-border-color: cyan; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;";
                    break;
                case 3:
                    style = "-fx-cursor: hand; -fx-border-color: purple; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;";
                    break;

                default:
                    style = "-fx-cursor: hand; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;";
                    break;
            }
            button.setStyle(style);
            game.setSelectedButtonIndex(0);
            game.setSelectedButton(null);
        } else if(game.getSelectedButton() != null && game.getSelectedButton() != button) {
            buyButton.setText("Compra Casa");
            // deselect and select
            String style;
            switch (gameManager.getPropertyOwnerIndex(game.getSelectedButtonIndex())) {
                case 0:
                    style = "-fx-cursor: hand; -fx-border-color: green; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;";
                    break;
                case 1:
                    style = "-fx-cursor: hand; -fx-border-color: yellow; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;";
                    break;
                case 2:
                    style = "-fx-cursor: hand; -fx-border-color: cyan; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;";
                    break;
                case 3:
                    style = "-fx-cursor: hand; -fx-border-color: purple; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;";
                    break;

                default:
                    style = "-fx-cursor: hand; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;";
                    break;
            }
            game.getSelectedButton().setStyle(style);
            button.setStyle("-fx-cursor: hand; -fx-border-color: red; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
            game.setSelectedButtonIndex(index);
            game.setSelectedButton(button);
        } else {
            buyButton.setText("Compra Casa");
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
