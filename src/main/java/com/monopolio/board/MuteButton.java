package com.monopolio.board;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.Objects;

public class MuteButton extends Button {
    private boolean muted;
    private ImageView playIcon;
    private ImageView muteIcon;

    public MuteButton() {
        this.muted = false;
        design();

        // Carica le immagini per il pulsante play e il pulsante mute
        Image playImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/rolldice.png")),50,50,true,true);
        Image muteImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/rolldice.png")),50,50,true,true);

        // Crea le ImageView per le immagini
        playIcon = new ImageView(playImage);
        muteIcon = new ImageView(muteImage);

        // Imposta l'icona di default a playIcon
        setGraphic(playIcon);

        // listener per il pulsante
        setOnAction(event -> {
            if (muted) {
                setGraphic(muteIcon);
                // logica per disattivare l'audio
            } else {
                setGraphic(playIcon);
                // logica per attivare l'audio
            }
        });
    }

    public void design() {
        setPrefSize(50, 50);
        setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), new CornerRadii(10), Insets.EMPTY)));
        setTextFill(Color.WHITE);
        setStyle(getStyle() + "-fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 15;");
    }
}
