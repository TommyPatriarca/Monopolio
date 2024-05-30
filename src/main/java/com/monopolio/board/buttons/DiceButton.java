package com.monopolio.board.buttons;

import com.monopolio.listeners.DiceListener;
import com.monopolio.managers.GameManager;
import com.monopolio.ui.Game;
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

/**
 * Gestisce il bottone dei dadi.
 */
public class DiceButton extends Button {
    private Game game;
    private GameManager gameManager;
    private boolean isRolled = false;
    private int value;
    private Image img;
    private ImageView view;

    public DiceButton(GameManager gameManager, Game game) {
        this.game = game;
        setDefault();
        design();
        setOnAction(new DiceListener(this, gameManager, game));
    }

    /**
     * Imposta un'immagine predefinita per il pulsante dei dadi.
     */
    public void setDefault() {
        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/rolldice.png")));
        view = new ImageView(img);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        setGraphic(view);
    }

    /**
     * Imposta stile del pulsante.
     */
    public void design() {
        setPrefSize(100, 100);
        setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setStyle("-fx-cursor: hand; -fx-background-radius: 15; -fx-background-color: #38b000; -fx-font-weight: bold;");
    }

    /**
     * Imposta il colore del bottone dei dadi una volta premuti.
     */
    public void colorRed() {
        setPrefSize(100, 100);
        setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setStyle("-fx-cursor: hand; -fx-background-radius: 15; -fx-background-color: #dd0426; -fx-font-weight: bold;");
    }

    /**
     * Imposta il colore del bottone dei dadi prima che vengano premuti.
     */
    public void colorGreen() {
        setPrefSize(100, 100);
        setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setStyle("-fx-cursor: hand; -fx-background-radius: 15; -fx-border-width: 2px; -fx-background-color: #38b000; -fx-font-weight: bold;");
    }

    /**
     * Abilita i bottoni.
     */
    public void enable() {
        colorGreen();
        isRolled = false;
        setDisabled(false);
    }

    /**
     * Disabilita i bottoni.
     */
    public void disable() {
        colorRed();
        isRolled = true;
        setDisabled(true);
    }

    /**
     * Permette di tirare i dadi.
     * @return i valore dei dadi.
     */
    public int roll() {
        value = RandUtils.Integer(1,4);

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

    /**
     * @return se il dado è stato tirato o no.
     */
    public boolean isRolled() {
        return isRolled;
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
