package com.monopolio.board.buttons;

import com.monopolio.managers.AlertManager;
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
 * Gestisce bottone regole.
 */
public class RulesButton extends Button {
    private Game game;

    public RulesButton(Game game)
    {
        this.game=game;
        design();
        Image playImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/rules.png")),50,50,true,true);
        // Crea le ImageView per le immagini
        ImageView icon = new ImageView(playImage);
        // Imposta l'icona di default a playIcon
        setOnAction(event -> AlertManager.showRules());
        setGraphic(icon);
    }

    /**
     * Imposta stile bottone.
     */
    public void design() {
        setPrefSize(50, 50);
        setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setStyle(getStyle() + "-fx-cursor: hand; -fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 15;");
    }
}
