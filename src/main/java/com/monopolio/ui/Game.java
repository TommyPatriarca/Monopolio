package com.monopolio.ui;

import com.monopolio.board.*;
import com.monopolio.board.boxes.*;
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
    Board[] cities = new Board[36];

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
                    Button button = createButton(number-1);
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

    /*
        if (cities[0] instanceof City) {
            City city = (City) cities[0];
            city.metodo(); // Chiamata al metodo specifico di MiaClasse
        } else {
            System.out.println("L'oggetto non è un'istanza di MiaClasse");
        }
    */

    private Button createButton(int number) {
        switch (number){
            case 0:
                cities[number] = new Start();
                break;
            case 1:
                cities[number] = new City("Delebio", 100, 50, 200, 10);
                break;
            case 2:
                cities[number] = new Chances();
                break;
            case 3:
                cities[number] = new City("Traona", 100, 50, 200, 10);
                break;
            case 4:
                cities[number] = new Taxes();
                break;
            case 5:
                cities[number] = new City("Morbegno", 100, 50, 200, 10);
                break;
            case 6:
                cities[number] = new City("Albaredo", 100, 50, 200, 10);
                break;
            case 7:
                cities[number] = new City("Pescegallo", 100, 50, 200, 10);
                break;
            case 8:
                cities[number] = new City("Talamona", 100, 50, 200, 10);
                break;
            case 9:
                cities[number] = new Prison();
                break;
            case 10:
                cities[number] = new City("Ardenno", 100, 50, 200, 10);
                break;
            case 11:
                cities[number] = new City("Villapinta", 100, 50, 200, 10);
                break;
            case 12:
                cities[number] = new City("Berbenno", 100, 50, 200, 10);
                break;
            case 13:
                cities[number] = new City("Castione", 100, 50, 200, 10);
                break;
            case 14:
                cities[number] = new City("Albosaggia", 100, 50, 200, 10);
                break;
            case 15:
                cities[number] = new City("Sondrio", 100, 50, 200, 10);
                break;
            case 16:
                cities[number] = new City("Lanzada", 100, 50, 200, 10);
                break;
            case 17:
                cities[number] = new City("Chiesa", 100, 50, 200, 10);
                break;
            case 18:
                cities[number] = new Parking();
                break;
            case 19:
                cities[number] = new City("Pianteda", 100, 50, 200, 10);
                break;
            case 20:
                cities[number] = new City("Chiuro", 100, 50, 200, 10);
                break;
            case 21:
                cities[number] = new City("San Giacomo", 100, 50, 200, 10);
                break;
            case 22:
                cities[number] = new City("Tresenda", 100, 50, 200, 10);
                break;
            case 23:
                cities[number] = new City("Aprica", 100, 50, 200, 10);
                break;
            case 24:
                cities[number] = new City("Tirano", 100, 50, 200, 10);
                break;
            case 25:
                cities[number] = new City("Grosio", 100, 50, 200, 10);
                break;
            case 26:
                cities[number] = new City("Sondalo", 100, 50, 200, 10);
                break;
            case 27:
                cities[number] = new ToPrison();
                break;
            case 28:
                cities[number] = new City("Livigno", 100, 50, 200, 10);
                break;
            case 29:
                cities[number] = new Unexpected();
                break;
            case 30:
                cities[number] = new Stations();
                break;
            case 31:
                cities[number] = new City("che", 100, 50, 200, 10);
                break;
            case 32:
                cities[number] = new City("altri", 100, 50, 200, 10);
                break;
            case 33:
                cities[number] = new City("posti", 100, 50, 200, 10);
                break;
            case 34:
                cities[number] = new City("mettere", 100, 50, 200, 10);
                break;
            case 35:
                cities[number] = new City("della", 100, 50, 200, 10);
                break;

            default:
                System.out.println("Could not find city....");
        }

        Button button = new Button(cities[number].getNome());

        if(number == 0 || number == 9 || number == 18 || number == 27)
        {
            button.setPrefSize(110, 110);
        }
        else if(number>0&&number<9 || number>18&&number<27)
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
        button.setOnAction(new ButtonListener(cities[number].getNome()));

        return button;
    }
}
