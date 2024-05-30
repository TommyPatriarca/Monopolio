package com.monopolio.board.buttons;

import com.monopolio.listeners.SellListener;
import com.monopolio.managers.GameManager;
import com.monopolio.ui.Game;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

// TODO stessa cosa del BuyButton

/**
 * Gestisce bottone vendita.
 */
public class SellButton extends Button {
    private Game game;

    public SellButton(Game game, GameManager gameManager) {
        this.game = game;
        setOnAction(new SellListener(game, gameManager));
        design();
    }

    /**
     * Imposta stile bottone.
     */
    public void design() {
        setPrefSize(100, 50);
        setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setText("Vendi");
        setStyle(getStyle() + "-fx-cursor: hand; -fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 15;");
    }
}
