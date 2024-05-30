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
 * Gestisce il bottone probabilità.
 */
public class ChancesButton extends Button {
    // TODO: recode for the chances.

    private int value;
    private Image img;
    private ImageView view;

    public ChancesButton() {
        setDefault();
        design();
        //setOnAction(new ChancesListner(this));
    }

    public ChancesButton(GameManager gameManager, Game game) {
        setDefault();
        design();
    }

    /**
     * Imposta un'immagine predefinita per il pulsante probabilità.
     */
    public void setDefault() {
        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/chances.png")));
        view = new ImageView(img);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        setGraphic(view);
    }

    /**
     * Imposta stile bottone.
     */
    public void design() {
        setPrefSize(100, 100);
        setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setStyle(getStyle() + "-fx-cursor: hand; -fx-background-color: #f77f00; -fx-text-fill: white; -fx-background-radius: 15;");
    }

    /**
     * @return l'immagine associata al pulsante delle probabilità.
     */
    public ImageView getView() {
        return view;
    }

    /**
     * @return il valore associato al bottone delle probabilità.
     */
    public int getValue() {
        return value;
    }
}