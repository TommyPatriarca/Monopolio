package com.monopolio.ui;

import com.monopolio.board.boxes.*;
import com.monopolio.board.buttons.*;
import com.monopolio.managers.GameManager;
import com.monopolio.player.Player;
import com.monopolio.listeners.BoxListener;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Game extends Application {
    private GameManager gameManager = new GameManager(this);
    private Image img;
    private ImageView view;
    private StackPane[] cells;
    private ListView<String> logListView;
    private ObservableList<String> logItems;
    private BorderPane root;
    private Button[] buttons = new Button[32];
    private Button selectedButton = null;
    private int selectedButtonIndex = 0;

    //private LogManager logManager = new LogManager();

    public Game(String[] playerNames) {
        cells = new StackPane[32];
        // Creazione degli oggetti Player basati sui nomi dei giocatori
        for (int i = 0; i < 4; i++) {
            String playerName = playerNames[i];
            if (!playerName.isEmpty()) {
                gameManager.setPlayer(i, new Player(playerName));
            } else {
                gameManager.setPlayer(i, new Player(""));
            }
        }

        //Inizializza la lista dei log
        logItems = FXCollections.observableArrayList();
        logListView = new ListView<>(logItems);
        logListView.setPrefWidth(200);
    }

    @Override
    public void start(Stage primaryStage) {
        // Creazione del layout principale
        root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Creazione della VBox per i nomi dei giocatori

        refreshPlayersGUI();

        // Creazione del GridPane per il gioco
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Aggiunta bottoni al perimetro del GridPane
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
                    cells[number-1] = createCell(number - 1, 30);
                    gridPane.add(cells[number-1], j, i);
                }
            }
        }

        gameManager.setDice(0, new DiceButton(gameManager, this));
        gameManager.setDice(1, new DiceButton(gameManager, this));

        // end turn
        TurnButton endTurnButton = new TurnButton(gameManager, primaryStage);
        gridPane.add(endTurnButton, 4, 7);

        // buy button
        BuyButton buyButton = new BuyButton(gameManager);
        gridPane.add(buyButton, 2, 7);

        // sell button
        SellButton sellButton = new SellButton(this, gameManager);
        gridPane.add(sellButton, 6, 7);

        // grid pane add dice 0 a (2,2)
        gridPane.add(gameManager.getDice(0), 2, 2);
        gridPane.add(gameManager.getDice(1), 3, 2);

        gridPane.add(gameManager.getChancesButton(), 5, 2);
        gridPane.add(gameManager.getTreasuresButton(), 6, 2);

        // Aggiunta del GridPane al centro del BorderPane
        root.setCenter(gridPane);

        // Creazione di un nuovo HBox per i pulsanti
        VBox toolbarBox = new VBox();
        toolbarBox.setAlignment(Pos.TOP_RIGHT);
        toolbarBox.setSpacing(10);
        toolbarBox.setPadding(new Insets(10));

        MuteButton muteButton = new MuteButton();
        toolbarBox.getChildren().add(muteButton);

        //TradeButton tradeButton = new TradeButton();
        //toolbarBox.getChildren().add(tradeButton);

        RulesButton rulesButton = new RulesButton(this);
        toolbarBox.getChildren().add(rulesButton);

        InfoButton infoButton = new InfoButton(gameManager, this);
        toolbarBox.getChildren().add(infoButton);

        //SettingsButton settingsButton = new SettingsButton(this, primaryStage);
        //toolbarBox.getChildren().add(settingsButton);

        root.setRight(toolbarBox);

        // Creazione della scena
        Scene scene = new Scene(root, 800, 600);

        // Impostazioni finestra
        primaryStage.setTitle("Monopolio - Gioco");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Creazione del StackPane per sovrapporre i nodi
        StackPane centerStackPane = new StackPane();
        centerStackPane.setAlignment(Pos.TOP_CENTER);
        centerStackPane.setPadding(new Insets(10));

        // Creazione del ListView per i log
        /*
        ListView<String> logListView = new ListView<>(logItems);
        logListView.setPrefSize(400, 300);
        // Aggiungi il ListView dei log al centro del StackPane
        centerStackPane.getChildren().add(logListView);

        // Aggiungi il GridPane al centro del StackPane
        centerStackPane.getChildren().add(gridPane);

        // Aggiungi il StackPane al centro del BorderPane
        root.setCenter(centerStackPane);
         */

        gameManager.startGame();
    }

    private StackPane createCell(int number, int i) {
        gameManager.initCity(number);

        // Creazione del pulsante con l'immagine di sfondo
        Button button = new Button(gameManager.getCity(number).getNome());
        button.setPrefSize(110, 110);
        button.setBackground(new Background(new BackgroundFill(Color.web("#001845FF"), new CornerRadii(10), Insets.EMPTY)));
        button.setTextFill(Color.WHITE);
        button.setStyle(button.getStyle() + "-fx-cursor: hand; -fx-border-radius: 10; -fx-border-width: 2px; -fx-font-weight: bold;");
        button.setEffect(new DropShadow(10, Color.BLACK));

        if (gameManager.getCity(number) instanceof City city) {
            img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(city.getGroup().getPath())));
            setImage(button);
        } else if (gameManager.getCity(number) instanceof Stations stations) {
            img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/groups/train.png")));
            setImage(button);
        }

        // Aggiungi listener alla casella
        button.setOnAction(new BoxListener(gameManager, this, button, number));

        // Creazione della StackPane e aggiunta del pulsante
        StackPane cell = new StackPane();
        cell.setAlignment(Pos.CENTER);

        buttons[number] = button;

        // Aggiungi il pulsante al StackPane
        cell.getChildren().add(button);

        // Aggiungi le icone dei giocatori al StackPane
        ImageView[] playerIcons = createPlayerIcons(number);
        for (ImageView icon : playerIcons) {
            cell.getChildren().add(icon);
        }

        return cell;
    }

    public ImageView[] createPlayerIcons(int number) {
        Player[] players = gameManager.getPlayers();
        ImageView[] icons = new ImageView[4];
        int index = 0;
        int iconSize = 100; // or the size you use for creating player icons
        int j = 0;

        for (Player player : players) {
            if (player.getPosition() == number && !player.getName().isEmpty()) {
                player.setOldPosition(number);
                String imagePath = switch (j) {
                    case 0 -> "/images/pawns/verde.png";
                    case 1 -> "/images/pawns/giallo.png";
                    case 2 -> "/images/pawns/azzurro.png";
                    case 3 -> "/images/pawns/viola.png";
                    default -> throw new IllegalStateException("Unexpected value: " + j);
                };
                Image playImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), iconSize, iconSize, true, true);
                icons[index++] = new ImageView(playImage);
            }
            j++;
        }

        // Rimuovi le voci null
        return Arrays.stream(icons).filter(Objects::nonNull).toArray(ImageView[]::new);
    }

    public void removePlayerIcons(Player player, StackPane cell) {
        Player[] players = gameManager.getPlayers();
        Image playerImage = null;
        int iconSize = 100; // or the size you use for creating player icons
        int j = 0;

        // Find the image for the specified player
        for (Player tmpPlayer : players) {
            if (tmpPlayer.equals(player)) {
                String imagePath = switch (j) {
                    case 0 -> "/images/pawns/verde.png";
                    case 1 -> "/images/pawns/giallo.png";
                    case 2 -> "/images/pawns/azzurro.png";
                    case 3 -> "/images/pawns/viola.png";
                    default -> throw new IllegalStateException("Unexpected value: " + j);
                };
                playerImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), iconSize, iconSize, true, true);
                break;
            }
            j++;
        }

        if (playerImage == null) {
            throw new IllegalArgumentException("Player not found in the game manager's players list.");
        }

        // Logging player image details for debugging
        System.out.println("Player image ARGB at (0, 0): " + playerImage.getPixelReader().getArgb(0, 0));

        // Find and remove all instances of the player's icon from the cell
        List<ImageView> iconsToRemove = new ArrayList<>();
        for (Node node : cell.getChildren()) {
            if (node instanceof ImageView imageView) {
                System.out.println("Checking image in cell: " + imageView.getImage());
                if (compareImages(imageView.getImage(), playerImage)) {
                    iconsToRemove.add(imageView);
                    System.out.println("Marked player icon for removal.");
                }
            }
        }

        if (!iconsToRemove.isEmpty()) {
            cell.getChildren().removeAll(iconsToRemove);
            System.out.println("Removed player icon(s) from cell.");
        } else {
            System.out.println("Player icon not found in the cell.");
        }
    }

    // Helper method to compare images more thoroughly
    private boolean compareImages(Image img1, Image img2) {
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            return false;
        }

        PixelReader reader1 = img1.getPixelReader();
        PixelReader reader2 = img2.getPixelReader();

        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                if (reader1.getArgb(x, y) != reader2.getArgb(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }


    private void setImage(Button button) {
        view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        button.setGraphic(view);
    }

    public StackPane getCell(int index) {
        return cells[index];
    }

    public StackPane[] getCells() {
        return cells;
    }

    public Button getSelectedButton() {
        return selectedButton;
    }

    public void setSelectedButton(Button selectedButton) {
        this.selectedButton = selectedButton;
    }

    public int getSelectedButtonIndex() {
        return selectedButtonIndex;
    }

    public void setSelectedButtonIndex(int selectedButtonIndex) {
        this.selectedButtonIndex = selectedButtonIndex;
    }

    public void refreshPlayersGUI() {
        VBox playerNamesBox = new VBox();
        playerNamesBox.setAlignment(Pos.TOP_LEFT);
        playerNamesBox.setSpacing(10);
        playerNamesBox.setPadding(new Insets(10, 10, 10, 0));

        // Aggiungi i nomi dei giocatori alla VBox
        for (int i = 0; i < 4; i++) {
            if (!gameManager.getPlayer(i).getName().isEmpty()) {
                HBox playerBox = new HBox();
                playerBox.setAlignment(Pos.CENTER_LEFT);
                playerBox.setSpacing(5);

                // Aggiungi un pallino colorato
                Circle circle = new Circle(5);
                switch (i) {
                    case 0 -> circle.setFill(Color.GREEN);
                    case 1 -> circle.setFill(Color.YELLOW);
                    case 2 -> circle.setFill(Color.LIGHTBLUE);
                    case 3 -> circle.setFill(Color.PURPLE);
                }
                circle.setStroke(Color.WHITE);

                // Aggiungi il nome del giocatore
                Label playerNameLabel = new Label(gameManager.getPlayer(i).getName());
                playerNameLabel.setTextFill(Color.WHITE);

                // Aggiungi contatore soldi dei giocatori
                Label playerMoneyLabel = new Label("\uD83D\uDCB8" + "  " + gameManager.getPlayer(i).getMoney());
                playerMoneyLabel.setTextFill(Color.LIGHTGREEN);

                // Aggiungi il pallino e il nome del giocatore all'HBox
                playerBox.getChildren().addAll(circle, playerNameLabel, playerMoneyLabel);

                // Aggiungi l'HBox alla VBox
                playerNamesBox.getChildren().add(playerBox);
            }
        }

        root.setLeft(playerNamesBox);
    }

    public Button getButton(int index) {
        return buttons[index];
    }
}
