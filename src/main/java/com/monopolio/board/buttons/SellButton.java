package com.monopolio.board.buttons;

import com.monopolio.managers.GameManager;
import com.monopolio.player.Player;
import com.monopolio.ui.Game;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

// TODO stessa cosa del BuyButton
public class SellButton extends Button {
    private GameManager gameManager;

    public SellButton(GameManager gameManager) {
        this.gameManager = gameManager;
        design();
    }

    public void design() {
        setPrefSize(110, 50);
        setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setText("Vendi");
        setStyle(getStyle() + "-fx-cursor: hand; -fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 15;");
    }
}
