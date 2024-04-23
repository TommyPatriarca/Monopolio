package com.monopolio.ui;

import com.monopolio.board.City;
import com.monopolio.player.Player;
import com.monopolio.listeners.ButtonListener;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Game extends Application {
    Player[] players = new Player[4];
    City[] cities = new City[36];

    public Game(String[] playerNames) {
        // Creazione degli oggetti Player basati sui nomi dei giocatori
        for (int i = 0; i < 4; i++) {
            String playerName = playerNames[i];
            if (!playerName.isEmpty()) {
                players[i] = new Player(playerName);
            } else {
                players[i] = new Player("");
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Creazione del layout principale
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Creazione della VBox per i nomi dei giocatori
        VBox playerNamesBox = new VBox();
        playerNamesBox.setAlignment(Pos.TOP_LEFT);
        playerNamesBox.setSpacing(10);
        playerNamesBox.setPadding(new Insets(10, 0, 20, 10));

        // Aggiungi i nomi dei giocatori alla VBox
        for (int i=0;i<4;i++) {
            if(!players[i].getNome().equals("")){
            HBox playerBox = new HBox();
            playerBox.setAlignment(Pos.CENTER_LEFT);
            playerBox.setSpacing(5);

            // Aggiungi un pallino colorato
            Circle circle = new Circle(5);
            switch (i){
                case 0 -> {
                    circle.setFill(Color.RED);
                }
                case 1 -> {
                    circle.setFill(Color.YELLOW);
                }
                case 2 -> {
                    circle.setFill(Color.BLUEVIOLET);
                }
                case 3 -> {
                    circle.setFill(Color.GREEN);
                }
            }
            circle.setStroke(Color.WHITE); //

            // Aggiungi il nome del giocatore
            Label playerNameLabel = new Label(players[i].getNome());
            playerNameLabel.setTextFill(Color.WHITE);

            // Aggiungi il pallino e il nome del giocatore all'HBox
            playerBox.getChildren().addAll(circle, playerNameLabel);

            // Aggiungi l'HBox alla VBox
            playerNamesBox.getChildren().add(playerBox);
            }
        }

        root.setTop(playerNamesBox);


        // Creazione del GridPane per il gioco
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Aggiunta bottoni al perimetro del GridPane
        int count = 0; // Contatore per la numerazione dei bottoni
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
                    Button button = createButton(number);
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
    private Button createButton(int number) {
        String city;
        switch (number){
            case 0:
                city = "VIA";
                //cities[0] = new City("Via");
                break;
            case 1: city = "Delebio"; break;
            case 2: city = "PROBABILITA"; break;
            case 3: city = "Traona"; break;
            case 4: city = "TASSE"; break;
            case 5: city = "Morbegno"; break;
            case 6: city = "Albaredo"; break;
            case 7: city = "Pescegallo"; break;
            case 8: city = "Talamona"; break;
            case 9: city = "PRIGIONE"; break;
            case 10: city = "Ardenno"; break;
            case 11: city = "Villapinta"; break;
            case 12: city = "Berbenno"; break;
            case 13: city = "Castione"; break;
            case 14: city = "Albosaggia"; break;
            case 15: city = "Sondrio"; break;
            case 16: city = "Lanzada"; break;
            case 17: city = "Chiesa"; break;
            case 18: city = "PARCHEGGIO\n  GRATUITO"; break;
            case 19: city = "Pianteda"; break;
            case 20: city = "Chiuro"; break;
            case 21: city = "San Giacomo"; break;
            case 22: city = "Tresenda"; break;
            case 23: city = "Aprica"; break;
            case 24: city = "Tirano"; break;
            case 25: city = "Grosio"; break;
            case 26: city = "Sondalo"; break;
            case 27: city = "  VAI IN\nPRIGIONE"; break;
            case 28: city = "Livigno"; break;
            case 29: city = "non"; break;
            case 30: city = "so"; break;
            case 31: city = "che"; break;
            case 32: city = "altri"; break;
            case 33: city = "posti"; break;
            case 34: city = "mettere"; break;
            case 35: city = "della"; break;

            default: city = "damn";
        }

        Button button = new Button(city);

        if(number == 1 || number == 10 || number == 19 || number == 28)
        {
            button.setPrefSize(110, 110);
        }
        else if(number>1&&number<10 || number>19&&number<28)
        {
            button.setPrefSize(90, 110);
        }else
        {
            button.setPrefSize(110, 90);
        }

        button.setBackground(new Background(new BackgroundFill(Color.web("#001845FF"), new CornerRadii(10), Insets.EMPTY)));
        button.setTextFill(Color.WHITE);

        //TODO creare tutti i gruppi di bottoni di colori diversi e fare le caselle speciali

        button.setStyle("-fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
        button.setEffect(new DropShadow(10, Color.BLACK));

        // Aggiungi listener al pulsante
        button.setOnAction(new ButtonListener(city));

        return button;
    }
}
