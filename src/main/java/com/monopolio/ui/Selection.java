package com.monopolio.ui;

import com.monopolio.Monopolio;
import com.monopolio.cli.Cli;
import com.monopolio.managers.InterfaceManager;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Objects;

/**
 * Permette di scegliere in che modalità avviare il gioco: Gui o Cli.
 */
public class Selection extends Application {
    private Cli cli = new Cli();

    Image img;
    Image bkg;
    int selection;
    Stage stage;
    Monopolio monopolio = new Monopolio();
    private double xOffset = 0;
    private double yOffset = 0;

    /**
     * Avvia l'applicazione.
     * @param stage è la finestra dell'applicazione.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;

        // Creazione dei bottoni CLI e GUI
        Button cliButton = createButton("CLI");
        Button guiButton = createButton("GUI");
        Button exitButton = createButton("EXIT");

        // Gestione degli eventi di clic
        cliButton.setOnAction(e -> handleCliSelection());
        guiButton.setOnAction(e -> handleGuiSelection());
        exitButton.setOnAction(e -> handleExitSelection());

        cliButton.setTranslateY(100);
        guiButton.setTranslateY(100);
        exitButton.setTranslateY(100);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(cliButton, guiButton, exitButton);
        layout.setAlignment(Pos.CENTER);

        // Creazione della barra del titolo personalizzata
        HBox titleBar = createCustomTitleBar();

        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setCenter(layout);

        // Caricamento dell'immagine di sfondo
        bkg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/selection2.png")));
        BackgroundImage backgroundImage = new BackgroundImage(bkg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        // Creazione della scena
        Scene scene = new Scene(root, 500, 650);

        img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/buildings.png")));
        stage.getIcons().add(img);

        // Configurazione dello stage
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Selezione Interfaccia");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Crea la barra del titolo personalizzata
     * @return
     */
    private HBox createCustomTitleBar() {
        HBox titleBar = new HBox();
        titleBar.setStyle("-fx-background-color: transparent; -fx-padding: 10px;");

        // Regione per spingere il pulsante di chiusura a destra
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button closeButton = new Button("x");
        closeButton.setStyle("-fx-cursor: hand; -fx-background-color: transparent; -fx-text-fill: white;");
        closeButton.setOnAction(e -> stage.close());

        titleBar.getChildren().addAll(spacer, closeButton);

        titleBar.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        titleBar.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        // Aggiunta di margini per abbassare la barra del titolo
        VBox.setMargin(titleBar, new Insets(10, 0, 0, 0));

        return titleBar;
    }

    /**
     * Crea un bottone.
     * @param text rappresenta ciò che verrà scritto sul bottone.
     * @return il bottone.
     */
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-font-size: 16px; -fx-pref-width: 150px; -fx-pref-height: 40px; -fx-background-radius: 20px; -fx-border-radius: 20px;");

        // Aggiunta dell'effetto di Glow
        DropShadow glow = new DropShadow();
        glow.setColor(Color.CYAN);
        glow.setWidth(30);
        glow.setHeight(30);
        button.setOnMouseEntered(e -> button.setEffect(glow));
        button.setOnMouseExited(e -> button.setEffect(null));

        // Aggiunta dell'effetto di Zoom
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        button.setOnMousePressed(e -> scaleTransition.play());
        button.setOnMouseReleased(e -> scaleTransition.stop());

        return button;
    }

    /**
     * Metodo che gestisce la selezione CLI.
     */
    private void handleCliSelection() {
        Monopolio.setInterfaceType(InterfaceManager.InterfaceType.CLI);
        stage.close();
        monopolio.getCli().gameSelection();
    }

    /**
     * Metodo che gestisce la selezione GUI.
     */
    private void handleGuiSelection() {
        Monopolio.setInterfaceType(InterfaceManager.InterfaceType.GUI);
        stage.close();
        monopolio.start(new Stage());
    }

    /**
     * Metodo che gestice la selezione exit.
     */
    private void handleExitSelection() {
        System.out.println("EXIT selezionato");
        System.exit(0);
    }

    /**
     * @return la selezione scelta.
     */
    public int getSelection() {
        return selection;
    }

    /**
     * Avvia l'applicazione.
     * @param args contiene gli argomenti della riga di comando passati all'applicazione JavaFX.
     */
    public void start(String[] args) {
        launch(args);
    }
}
