package com.monopolio;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InterfacciaGioco extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creazione del layout principale
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Creazione del GridPane per il gioco
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Aggiunta bottoni al perimetro del GridPane
        int count = 1; // Contatore per la numerazione dei bottoni
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 || i == 9 || j == 0 || j == 9) {
                    int number;
                    if (i == 0) {
                        number = j + 1;
                    } else if (j == 9) {
                        number = 10 + i;
                    } else if (i == 9) {
                        number = 28 - j;
                    } else {
                        number = 37 - i ;
                    }
                    Button button = createButton(String.valueOf(number));
                    gridPane.add(button, j, i);
                }
            }
        }

        // Aggiunta del GridPane al centro del BorderPane
        root.setCenter(gridPane);

        // Creazione della scena
        Scene scene = new Scene(root, 800, 600);

        // Impostazioni finestra
        primaryStage.setTitle("Monopolio - Gioco");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String label) {
        Button button = new Button(label);
        button.setPrefSize(70, 70);
        button.setBackground(new Background(new BackgroundFill(Color.web("#001845FF"), new CornerRadii(10), Insets.EMPTY)));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-border-radius: 10;");
        button.setEffect(new DropShadow(10, Color.BLACK));

        // Aggiungi listener al pulsante
        button.setOnAction(new ButtonListener(label));

        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
