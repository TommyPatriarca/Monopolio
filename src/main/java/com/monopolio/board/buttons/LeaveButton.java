package com.monopolio.board.buttons;

import com.monopolio.listeners.BankruptListener;
import com.monopolio.listeners.InfoListener;
import com.monopolio.listeners.LeaveListener;
import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SceneManager;
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
 * Gestisce bottone di abbandono.
 */
public class LeaveButton extends Button {

    public LeaveButton(GameManager gameManager, SceneManager sceneManager)
    {
        design();
        Image playImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/quit.png")),50,50,true,true);
        // Crea le ImageView per le immagini
        ImageView icon = new ImageView(playImage);
        // Imposta l'icona di default a playIcon
        setOnAction(new LeaveListener(gameManager,sceneManager));
        setGraphic(icon);
    }

    /**
     * Imposta stile bottone.
     */
    public void design() {
        setPrefSize(50, 50);
        setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setStyle("-fx-cursor: hand; -fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 15;");
    }
}
