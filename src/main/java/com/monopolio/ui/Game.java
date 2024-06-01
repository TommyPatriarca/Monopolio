package com.monopolio.ui;

import com.monopolio.board.Box;
import com.monopolio.board.boxes.*;
import com.monopolio.board.buttons.*;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.LogManager;
import com.monopolio.managers.SceneManager;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Gestisce l'interfaccia grafica e la logica del gioco.
 */
public class Game extends Application implements Serializable {
    private SceneManager sceneManager;
    private GameManager gameManager;
    private Image img;
    private ImageView view;
    private StackPane[] cells;
    private ListView<String> logListView;
    private ObservableList<String> logItems;
    private BorderPane root;
    private Button[] buttons = new Button[32];
    private BuyButton buyButton;
    private SellButton sellButton;
    private Button selectedButton = null;
    private int selectedButtonIndex = 0;

    private LogManager logManager;

    public Game(SceneManager sceneManager, String[] playerNames) {
        gameManager = new GameManager(this);
        buyButton = new BuyButton(gameManager, this);
        sellButton = new SellButton(this, gameManager);
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

        this.sceneManager = sceneManager;
    }

    // Load game
    public Game(SceneManager sceneManager, Player[] players, Box[] cities) {
        gameManager = new GameManager(this, players, cities);
        buyButton = new BuyButton(gameManager, this);
        sellButton = new SellButton(this, gameManager);

        cells = new StackPane[32];

        //Inizializza la lista dei log
        logItems = FXCollections.observableArrayList();
        logListView = new ListView<>(logItems);
        logListView.setPrefWidth(200);

        this.sceneManager = sceneManager;
    }

    /**
     * Permette il riavvio del gioco.
     * @param primaryStage rappresenta la finestra principale dell'applicazione.
     */
    public void restart(Stage primaryStage) {// Initialize log manager with a label for displaying logs
        Label logLabel = new Label();
        logManager = new LogManager(logLabel);

        // Sample logs for testing
        logManager.log("Il gioco sta per iniziare.");
        logManager.log("Inizializzazione del layout principale.");
        logManager.log("Creazione della griglia di gioco.");

        root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Creazione della VBox per i nomi dei giocatori

        refreshPlayersGUI();

        // Creazione del GridPane per il gioco
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(3);
        gridPane.setVgap(3);

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
                    cells[number - 1] = createCell(number - 1, true);
                    gridPane.add(cells[number - 1], j, i);
                }
            }
        }

        gameManager.setDice(0, new DiceButton(gameManager, this));
        gameManager.setDice(1, new DiceButton(gameManager, this));

        // end turn
        TurnButton endTurnButton = new TurnButton(gameManager, this);
        gridPane.add(endTurnButton, 4, 7);

        // buy button
        gridPane.add(buyButton, 2, 7);
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

        SaveButton saveButton = new SaveButton(gameManager);
        toolbarBox.getChildren().add(saveButton);

        BankruptButton bankruptButton = new BankruptButton(gameManager, this, sceneManager);
        toolbarBox.getChildren().add(bankruptButton);

        LeaveButton leaveButton = new LeaveButton(gameManager, sceneManager);
        toolbarBox.getChildren().add(leaveButton);

        //SettingsButton settingsButton = new SettingsButton(this, primaryStage);
        //toolbarBox.getChildren().add(settingsButton);

        root.setRight(toolbarBox);

        // Creazione della scena
        Scene scene = new Scene(root, 800, 600);

        // Impostazioni finestra
        primaryStage.setTitle("Monopolio - Gioco");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Adding the log label to the UI
        VBox logBox = new VBox();
        logBox.setPadding(new Insets(10));
        logBox.getChildren().add(logLabel);

        root.setBottom(logBox);

        logManager.setMainLog("E' il turno del giocatore: " + gameManager.getCurrentPlayer().getName());

        gameManager.refreshOutlines();
    }

    /**
     * Avvia il gioco.
     * @param primaryStage rappresenta la finestra principale dell'applicazione.
     */
    @Override
    public void start(Stage primaryStage) {
        // Initialize log manager with a label for displaying logs
        Label logLabel = new Label();
        logManager = new LogManager(logLabel);

        // Sample logs for testing
        logManager.log("Il gioco sta per iniziare.");
        logManager.log("Inizializzazione del layout principale.");
        logManager.log("Creazione della griglia di gioco.");

        root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.web("#001233FF"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Creazione della VBox per i nomi dei giocatori

        refreshPlayersGUI();

        // Creazione del GridPane per il gioco
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(3);
        gridPane.setVgap(3);

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
                    cells[number - 1] = createCell(number - 1, false);
                    gridPane.add(cells[number - 1], j, i);
                }
            }
        }

        gameManager.setDice(0, new DiceButton(gameManager, this));
        gameManager.setDice(1, new DiceButton(gameManager, this));

        // end turn
        TurnButton endTurnButton = new TurnButton(gameManager, this);
        gridPane.add(endTurnButton, 4, 7);

        // buy button
        gridPane.add(buyButton, 2, 7);
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

        SaveButton saveButton = new SaveButton(gameManager);
        toolbarBox.getChildren().add(saveButton);

        BankruptButton bankruptButton = new BankruptButton(gameManager, this, sceneManager);
        toolbarBox.getChildren().add(bankruptButton);

        LeaveButton leaveButton = new LeaveButton(gameManager, sceneManager);
        toolbarBox.getChildren().add(leaveButton);

        //SettingsButton settingsButton = new SettingsButton(this, primaryStage);
        //toolbarBox.getChildren().add(settingsButton);

        root.setRight(toolbarBox);

        // Creazione della scena
        Scene scene = new Scene(root, 800, 600);

        // Impostazioni finestra
        primaryStage.setTitle("Monopolio - Gioco");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Adding the log label to the UI
        VBox logBox = new VBox();
        logBox.setPadding(new Insets(10));
        logBox.getChildren().add(logLabel);

        root.setBottom(logBox);

        gameManager.startGame();

        logManager.setMainLog("E' il turno del giocatore: " + gameManager.getCurrentPlayer().getName());
    }

    /**
     * Crea una singola cella del gioco nella griglia.
     * @param number rappresenta il numero della casella nel tabellone di gioco.
     * @param load boolean se sta inizializzando nuove prorprietà o è un caricamento di un salvataggio.
     * @return la cella.
     */
    private StackPane createCell(int number, boolean load) {
        if(!load) {
            gameManager.initCity(number);
        }

        // Creazione del pulsante con l'immagine di sfondo
        Button button = new Button(gameManager.getCity(number).getNome());
        button.setPrefSize(100, 100);
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
        button.setOnAction(new BoxListener(buyButton, sellButton, gameManager, this, button, number));

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

    /**
     * Crea le icone dei giocatori.
     * @param number si riferisce al numero del giocatore
     * @return l'icona per ogni giocatore
     */
    public ImageView[] createPlayerIcons(int number) {
        Player[] players = gameManager.getPlayers();
        ImageView[] icons = new ImageView[4];
        int index = 0;
        int iconSize = 90;
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

    /**
     * Rimuove le icone dei giocatori.
     * @param player è il nome del giocatore da cui si vuole rimuovere l'icona.
     * @param cell indica la cella in cui il giocatore si trova.
     */
    public void removePlayerIcons(Player player, StackPane cell) {
        Player[] players = gameManager.getPlayers();
        Image playerImage = null;
        int iconSize = 90;
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
        }
    }

    /**
     * Confronta due immagini pixel per pixel per determinare se sono identiche.
     * @param img1 è la prima immagine da confrontare.
     * @param img2 è la seconda immagine da confrontare.
     * @return "true" se le immagini sono identiche pixel per pixel, "false" se non lo sono.
     */
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

    /**
     * Imposta l'immagine.
     * @param button è il bottono su cui viene settata l'immagine.
     */
    private void setImage(Button button) {
        view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        button.setGraphic(view);
    }

    /**
     * @param index si riferisce al numero della cella.
     * @return la cella con tale numero.
     */
    public StackPane getCell(int index) {
        return cells[index];
    }

    /**
     * @return tutte le celle.
     */
    public StackPane[] getCells() {
        return cells;
    }

    /**
     * @return ritorna il bottone selezionato.
     */
    public Button getSelectedButton() {
        return selectedButton;
    }

    /**
     * Imposta il bottone selezionato
     * @param selectedButton è il bottone.
     */
    public void setSelectedButton(Button selectedButton) {
        this.selectedButton = selectedButton;
    }

    /**
     * @return il numero del bottone selezionato.
     */
    public int getSelectedButtonIndex() {
        return selectedButtonIndex;
    }

    /**
     * Imposta il valore del bottone selzionato.
     * @param selectedButtonIndex è il valore del bottone selezionato.
     */
    public void setSelectedButtonIndex(int selectedButtonIndex) {
        this.selectedButtonIndex = selectedButtonIndex;
    }

    /**
     * Aggiorna l'interfaccia grafica dei giocatori.
     */
    public void refreshPlayersGUI() {
        VBox playerNamesBox = new VBox();
        playerNamesBox.setAlignment(Pos.TOP_LEFT);
        playerNamesBox.setSpacing(10);
        playerNamesBox.setPadding(new Insets(10));

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

    /**
     * @param index è il numero del bottone.
     * @return il bottone.
     */
    public Button getButton(int index) {
        return buttons[index];
    }

    /**
     * @return i log.
     */
    public LogManager getLogManager() {
        return logManager;
    }
}
