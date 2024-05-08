package com.monopolio.board.buttons;

import com.monopolio.listeners.TurnListener;
import com.monopolio.managers.GameManager;
import com.monopolio.ui.Game;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Objects;

public class TurnButton extends Button {
    private GameManager gameManager;

    public TurnButton(GameManager gameManager)
    {
        this.gameManager=gameManager;

        design();
        Image playImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/turn.png")),50,50,true,true);
        ImageView icon = new ImageView(playImage);
        setGraphic(icon);
        setOnAction(new TurnListener(gameManager));
    }


    public void design() {
        setPrefSize(50, 50);
        setBackground(new Background(new BackgroundFill(Color.web("#dd0426"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setStyle(getStyle() + "-fx-cursor: hand; -fx-background-color: #dd0426; -fx-text-fill: white; -fx-background-radius: 15;");
    }
}
