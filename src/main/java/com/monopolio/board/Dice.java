package com.monopolio.board;

import com.monopolio.listeners.DiceListener;
import com.monopolio.utils.RandUtils;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Dice extends Button {
    private int value;
    private Image img;
    private ImageView view;

    public Dice() {
        setDefault();
        design();
        setOnAction(new DiceListener(this));
    }

    public void setDefault() {
        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/rolldice.png")));
        view = new ImageView(img);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        setGraphic(view);
    }

    public void design() {
        setPrefSize(110, 110);
        setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setStyle(getStyle() + "-fx-cursor: hand; -fx-border-radius: 0; -fx-border-width: 0px; -fx-font-weight: bold;");
    }

    public int roll() {
        value = RandUtils.Integer(1,6);

        switch(value) {
            case 1 -> {
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/dice1.png")));
            }
            case 2 -> {
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/dice2.png")));
            }
            case 3 -> {
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/dice3.png")));
            }
            case 4 -> {
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/dice4.png")));
            }
            case 5 -> {
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/dice5.png")));
            }
            case 6 -> {
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/dice6.png")));
            }
        }

        view = new ImageView(img);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        setGraphic(view);

        return value;
    }

    public ImageView getView() {
        return view;
    }

    public int getValue() {
        return value;
    }
}
