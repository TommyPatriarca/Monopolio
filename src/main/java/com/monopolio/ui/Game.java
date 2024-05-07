package com.monopolio.ui;

import com.monopolio.board.*;
import com.monopolio.board.boxes.*;
import com.monopolio.board.buttons.*;
import com.monopolio.player.Player;
import com.monopolio.listeners.BoxListener;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

/**
 * DA FARE PIù AVANTI
 *
 *  SERVE PER NON RISCRIVERE TROOPPE VOLTE IL TESTO "MONO" E "POLIO"
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

public class Game extends Application {
    Player[] players = new Player[4];
    Box[] cities = new Box[36];
    DiceButton[] dices = new DiceButton[2];
    ChancesButton chancesButton = new ChancesButton();
    TreasuresButton treasuresButton = new TreasuresButton();
    private Image img;
    private ImageView view;
    private boolean set;

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
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Creazione della VBox per i nomi dei giocatori
        VBox playerNamesBox = new VBox();
        playerNamesBox.setAlignment(Pos.TOP_LEFT);
        playerNamesBox.setSpacing(10);
        playerNamesBox.setPadding(new Insets(10, 10, 10, 0));

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
                    circle.setFill(Color.GREEN);
                }
                case 1 -> {
                    circle.setFill(Color.YELLOW);
                }
                case 2 -> {
                    circle.setFill(Color.LIGHTBLUE);
                }
                case 3 -> {
                    circle.setFill(Color.PURPLE);
                }
            }
            circle.setStroke(Color.WHITE); //

            // Aggiungi il nome del giocatore
            Label playerNameLabel = new Label(players[i].getNome());
            playerNameLabel.setTextFill(Color.WHITE);

            // Aggiungi contatore soldi dei giocatori
             Label playerMoneyLabel = new Label("\uD83D\uDCB8" + "  " + players[i].getMoney());
             playerMoneyLabel.setTextFill(Color.LIGHTGREEN);

            // Aggiungi il pallino e il nome del giocatore all'HBox
            playerBox.getChildren().addAll(circle, playerNameLabel, playerMoneyLabel);

            // Aggiungi l'HBox alla VBox
            playerNamesBox.getChildren().add(playerBox);
            }
        }

        root.setLeft(playerNamesBox);

        // Creazione del GridPane per il gioco
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Aggiunta bottoni al perimetro del GridPane
        int count = 0; // Contatore per la numerazione dei bottoni
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 0 || i == 8 || j == 0 || j == 8) {
                    int number;
                    if (i == 0) {
                        number = j + 1;
                    } else if (j == 8) {
                        number = 9 + i;
                    } else if (i == 8) {
                        number = 25 - j;
                    } else {
                        number = 33 - i;
                    }
                    Button button = createButton(number - 1);
                    gridPane.add(button, j, i);
                }
            }
        }

        dices[0] = new DiceButton();
        dices[1] = new DiceButton();

        gridPane.add(dices[0], 2, 2);
        gridPane.add(dices[1], 3, 2);

        gridPane.add(chancesButton, 5, 2);
        gridPane.add(treasuresButton, 6, 2);

        // Aggiunta del GridPane al centro del BorderPane
        root.setCenter(gridPane);

        // Creazione di un nuovo HBox per i pulsanti
        VBox toolbarBox = new VBox();
        toolbarBox.setAlignment(Pos.TOP_RIGHT);
        toolbarBox.setSpacing(10);
        toolbarBox.setPadding(new Insets(10));

        MuteButton muteButton = new MuteButton();
        toolbarBox.getChildren().add(muteButton);

        TradeButton tradeButton = new TradeButton();
        toolbarBox.getChildren().add(tradeButton);

        RulesButton rulesButton = new RulesButton(this);
        toolbarBox.getChildren().add(rulesButton);
        rulesButton.setOnAction(event -> {
            showAlert(primaryStage);
        });

        root.setRight(toolbarBox);
        
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
                cities[number] = new StartBox(200);
                break;
            case 1:
                cities[number] = new City("Traona", 60, 50, 200, 10);
                break;
            case 2:
                cities[number] = new Chances();
                break;
            case 3:
                cities[number] = new City("Andalo", 60, 50, 200, 10);
                break;
            case 4:
                cities[number] = new Taxes(200);
                break;
            case 5:
                cities[number] = new City("Regoledo", 100, 50, 200, 10);
                break;
            case 6:
                cities[number] = new Treasures();
                break;
            case 7:
                cities[number] = new City("Morbegno", 100, 50, 200, 10);
                break;
            case 8:
                cities[number] = new Prison();
                break;
            case 9:
                cities[number] = new City("Talamona", 120, 50, 200, 10);
                break;
            case 10:
                cities[number] = new City("Ardenno", 140, 50, 200, 10);
                break;
            case 11:
                cities[number] = new Stations(Stations.StationTypes.EST);
                break;
            case 12:
                cities[number] = new City("Berbenno", 140, 50, 200, 10);
                break;
            case 13:
                cities[number] = new City("Castione", 160, 50, 200, 10);
                break;
            case 14:
                cities[number] = new City("Castiones", 160, 50, 200, 10);
                break;
            case 15:
                cities[number] = new Chances();
                break;
            case 16:
                cities[number] = new Parking();
                break;
            case 17:
                cities[number] = new City("Sondrio", 180, 50, 200, 10);
                break;
            case 18:
                cities[number] = new City("Chiesa", 180, 50, 200, 10);
                break;
            case 19:
                cities[number] = new City("Piantedo", 220, 50, 200, 10);
                break;
            case 20:
                cities[number] = new Treasures();
                break;
            case 21:
                cities[number] = new City("San Giacomo", 220, 50, 200, 10);
                break;
            case 22:
                cities[number] = new City("Tresenda", 240, 50, 200, 10);
                break;
            case 23:
                cities[number] = new City("Tirano", 260, 50, 200, 10);
                break;
            case 24:
                cities[number] = new ToPrison();
                break;
            case 25:
                cities[number] = new Stations(Stations.StationTypes.SUD);
                break;
            case 26:
                cities[number] = new City("Sondalo", 280, 50, 200, 10);
                break;
            case 27:
                cities[number] = new City("Grosio", 260, 50, 200, 10);
                break;
            case 28:
                cities[number] = new City("Livigno", 300, 50, 200, 10);
                break;
            case 29:
                cities[number] = new City("Trepalle", 300, 50, 200, 10);
                break;
            case 30:
                cities[number] = new Chances();
                break;
            case 31:
                cities[number] = new City("Bormio", 300, 50, 200, 10);
                break;

            default:
                System.out.println("Could not find city....");
        }

        Button button = new Button(cities[number].getNome());
        button.setPrefSize(110, 110);
        button.setBackground(new Background(new BackgroundFill(Color.web("#001845FF"), new CornerRadii(10), Insets.EMPTY)));
        button.setTextFill(Color.WHITE);
        button.setStyle(button.getStyle() + "-fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
        button.setEffect(new DropShadow(10, Color.BLACK));

        set=true;

        switch (number){

            case 1, 3:
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/groups/red.png")));
                break;
            case 5, 9, 7:
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/groups/yellow.png")));
                break;
            case 10, 13, 12:
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/groups/orange.png")));
                break;
            case 14, 18, 17:
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/groups/pink.png")));
                break;
            case 19, 22, 21:
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/groups/green.png")));
                break;
            case 23, 27, 26:
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/groups/cyan.png")));
                break;
            case 28, 31, 29:
                img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/groups/blue.png")));
                break;

            default:set=false;
        }
        if(set==true){
                view = new ImageView(img);
                view.setFitHeight(20);
                view.setPreserveRatio(true);
                button.setGraphic(view);
        }


        // Aggiungi listener al pulsante
        button.setOnAction(new BoxListener(cities[number].getNome()));

        return button;
    }



    private void showAlert(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(primaryStage); // Imposta la finestra genitore
        alert.setTitle("Regole");
        alert.setHeaderText(null);
        alert.setContentText("Obiettivo: Essere l'ultimo giocatore con soldi e proprietà.\n" +
                "Setup: Ogni giocatore inizia con una quantità di denaro e si muove attorno al tabellone acquistando proprietà e pagando affitti.\n" +
                "Turno: Lanci il dado e muovi il tuo pezzo. Puoi acquistare proprietà su cui atterri e costruire case o hotel per aumentare l'affitto.\n" +
                "Affitto: Se atterri su una proprietà di un altro giocatore, devi pagare l'affitto in base alle regole specifiche della proprietà.\n" +
                "Eventi speciali: Atterra su caselle \"Probabilità\" o \"Imprevisti\" per ricevere una carta che può darti vantaggi o svantaggi.\n" +
                "Prigione: Puoi finire in prigione per diverse ragioni. Puoi uscire pagando una multa o tentando di tirare un doppio al dado.\n" +
                "Fallimento: Se non puoi pagare un affitto o una tassa, devi vendere proprietà o dichiararti fallito. L'ultimo giocatore rimasto vince.");

        // Applica lo stile alert personalizzato
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #001845E0; -fx-border-radius: 10;");

        // Rimuove l'intestazione predefinita
        dialogPane.setHeader(null);

        dialogPane.getStyleClass().add("custom-alert");
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.initOwner(primaryStage);
        stage.initStyle(StageStyle.TRANSPARENT);

        // Modifica lo stile del contenuto del messaggio
        dialogPane.lookup(".content.label").setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-background-radius: 10;");

        // Modifica lo stile dei pulsanti dell'alert
        alert.getButtonTypes().forEach(buttonType -> {
            Button button = (Button) dialogPane.lookupButton(buttonType);
            button.setStyle("-fx-background-color: #1081F9; -fx-text-fill: white; -fx-background-radius: 10;");
        });

        alert.showAndWait();
    }
}
