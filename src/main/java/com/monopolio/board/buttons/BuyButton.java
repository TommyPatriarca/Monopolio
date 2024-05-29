package com.monopolio.board.buttons;

import com.monopolio.listeners.BuyListener;
import com.monopolio.managers.GameManager;
import com.monopolio.player.Player;
import com.monopolio.ui.Game;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;


// TODO fare in modo che il botone compri cio che si puo acquistare, ad esempio se nessuno ha quel terreno premendolo lo acquisti altrimenti se è tuo acquisti una casa
public class BuyButton extends Button {
    GameManager gameManager;

    public BuyButton(GameManager gameManager, Game game) {
        this.gameManager = gameManager;
        design();
        setOnAction(new BuyListener(gameManager, game));
    }

    public void design() {
        setPrefSize(100, 50);
        setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setText("Compra");
        setStyle(getStyle() + "-fx-cursor: hand; -fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 15;");
    }
}
