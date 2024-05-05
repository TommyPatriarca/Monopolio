package com.monopolio.board;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class MuteButton extends Button {
    private boolean muted;
    private ImageView playIcon;
    private ImageView muteIcon;

    public MuteButton() {
        this.muted = false;

        // Carica le immagini per il pulsante play e il pulsante mute
        Image playImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/rolldice.png")),50,50,true,true);
        Image muteImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/dices/rolldice.png")),50,50,true,true);

        // Crea le ImageView per le immagini
        playIcon = new ImageView(playImage);
        muteIcon = new ImageView(muteImage);

        // Imposta l'icona di default a playIcon
        setGraphic(playIcon);

        // Imposta il listener per il pulsante
        setOnAction(event -> {
            muted = !muted; // Inverte lo stato del pulsante

            // Cambia l'icona in base allo stato
            if (muted) {
                setGraphic(muteIcon);
                // Aggiungi qui la logica per disattivare l'audio
            } else {
                setGraphic(playIcon);
                // Aggiungi qui la logica per attivare l'audio
            }
        });
    }
}
