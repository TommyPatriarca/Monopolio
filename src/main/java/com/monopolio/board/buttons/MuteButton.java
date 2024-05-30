package com.monopolio.board.buttons;

import com.monopolio.listeners.MuteListener;
import com.monopolio.managers.SongManager;
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
 * Gestisce bottone muta.
 */
public class MuteButton extends Button {
    private boolean muted = false;
    private ImageView playIcon;
    private ImageView muteIcon;

    // File audioFile = new File("/audio/track.wav");
    SongManager songManager = new SongManager();

    public MuteButton() {
        songManager.play();
        design();
        Image playImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/audio/play.png")),50,50,true,true);
        // Crea le ImageView per le immagini
        playIcon = new ImageView(playImage);
        // Imposta l'icona di default a playIcon
        setGraphic(playIcon);

        // listener per il pulsante
        setOnAction(new MuteListener(this));
    }

    /**
     * Permette di attiavare/disattivare l'audio di gioco.
     */
    public void toggle() {
        muted = !muted;

        // Carica le immagini per il pulsante play e il pulsante mute
        Image playImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/audio/play.png")),50,50,true,true);
        Image muteImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/audio/mute.png")),50,50,true,true);

        // Crea le ImageView per le immagini
        playIcon = new ImageView(playImage);
        muteIcon = new ImageView(muteImage);

        if (muted) {
             songManager.mute();
            setGraphic(muteIcon);
            // logica per disattivare l'audio
        } else {
             songManager.unmute();
            setGraphic(playIcon);
            // logica per attivare l'audio
        }
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
