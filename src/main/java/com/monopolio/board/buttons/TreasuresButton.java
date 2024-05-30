package com.monopolio.board.buttons;

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

/**
 * Gestisce bottone tesori.
 */
public class TreasuresButton extends Button {
    // TODO: recode for the trasures.

    private int value;
    private Image img;
    private ImageView view;

    public TreasuresButton() {
        setDefault();
        design();
    }

    public TreasuresButton(GameManager gameManager, Game game) {
        setDefault();
        design();
    }

    /**
     * Imposta un'immagine predefinita per il pulsante dei tesori.
     */
    public void setDefault() {
        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/treasures.png")));
        view = new ImageView(img);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        setGraphic(view);
    }

    /**
     * Imposta stile del bottone.
     */
    public void design() {
        setPrefSize(100, 100);
        setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setStyle(getStyle() + "-fx-cursor: hand; -fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 15;");
    }

    /**
     * @return l'immagine del bottone.
     */
    public ImageView getView() {
        return view;
    }

    /**
     * @return il valore del bottone.
     */
    public int getValue() {
        return value;
    }
}