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
        gridPane.setHgap(5);
        gridPane.setVgap(5);

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
            case 1: city = "VIA"; break;
            case 2: city = "Delebio"; break;
            case 3: city = "Mantello"; break;
            case 4: city = "Traona"; break;
            case 5: city = "Cosio"; break;
            case 6: city = "Morbegno"; break;
            case 7: city = "Albaredo"; break;
            case 8: city = "Pescegallo"; break;
            case 9: city = "Talamona"; break;
            case 10: city = "PRIGIONE"; break;
            case 11: city = "Ardenno"; break;
            case 12: city = "Villapinta"; break;
            case 13: city = "Berbenno"; break;
            case 14: city = "Castione"; break;
            case 15: city = "Albosaggia"; break;
            case 16: city = "Sondrio"; break;
            case 17: city = "Lanzada"; break;
            case 18: city = "Chiesa"; break;
            case 19: city = "PARCHEGGIO\n  GRATUITO"; break;
            case 20: city = "Pianteda"; break;
            case 21: city = "Chiuro"; break;
            case 22: city = "San Giacomo"; break;
            case 23: city = "Tresenda"; break;
            case 24: city = "Aprica"; break;
            case 25: city = "Tirano"; break;
            case 26: city = "Grosio"; break;
            case 27: city = "Sondalo"; break;
            case 28: city = "  VAI IN\nPRIGIONE"; break;
            case 29: city = "Livigno"; break;
            case 30: city = "non"; break;
            case 31: city = "so"; break;
            case 32: city = "che"; break;
            case 33: city = "altri"; break;
            case 34: city = "posti"; break;
            case 35: city = "mettere"; break;
            case 36: city = "della"; break;

            default: city = "damn";
        }

        Button button = new Button(city);
        if(number == 1 || number == 10 || number == 19 || number == 28)
        {
            button.setPrefSize(130, 130);
        }
        else if(number>1&&number<10 || number>19&&number<28)
        {
            button.setPrefSize(90, 130);
        }else
        {
            button.setPrefSize(130, 90);
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


    public static void main(String[] args) {
        launch(args);
    }
}
