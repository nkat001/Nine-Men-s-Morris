package game;

import game.Actor.DoublePlayer;
import game.Actor.SinglePlayer;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Home class that contains UI elements to be displayed upon launch of the game
 *
 * Created by:
 *
 * @author Nethara
 * Modified by : Ethel Lim Jia Yee
 */

public class Home extends Application {
    private String player1Name;
    private String player2Name;
    private static final String HEADING = "Nine Men's Morris";
    private static final Duration LETTER_APPEARANCE = Duration.millis(150);
    private int currentLetterIndex = 0;
    private Text headingText;

    /**
     * start method to start application
     *
     * @param stage is the platform to show the application
     * @throws Exception for errors
     */
    @Override
    public void start(Stage stage) throws Exception {
        Rule.setSpMode(false);
        Rule.setTryStage( stage );
        StackPane pane = new StackPane();
        pane.setAlignment(Pos.CENTER);

        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();

        Stop[] stops = new Stop[]{
                new Stop(0, Color.LIGHTBLUE),
                new Stop(1, Color.LIGHTGREEN),
                new Stop(2, Color.DARKCYAN),
        };

        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill fill = new BackgroundFill(gradient, null, null);
        Background newBackground = new Background(fill);
        pane.setBackground(newBackground);

        Image image = new Image("game/board.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), imageView);
        rotateTransition.setByAngle(10);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
        double imageWidth = 500;
        double imageHeight = 500;
        imageView.setFitWidth(imageWidth);
        imageView.setFitHeight(imageHeight);
        imageView.setTranslateY(50);
        pane.getChildren().add(imageView);
        Font font = Font.font("Impact", FontWeight.BOLD, 85);

        headingText = new Text();
        headingText.setFont(font);
        headingText.setStyle("-fx-text-fill: black; -fx-effect: dropshadow(gaussian, white, 2, 2, 2, 2);");
        StackPane.setAlignment(headingText, Pos.TOP_CENTER);
        headingText.setTranslateY(20);
        pane.getChildren().add(headingText);
        letterTransition();

        // Player Vs Player button
        Button button = new Button("Player Vs Player");
        button.setScaleX(2.5);
        button.setScaleY(2.5);
        button.setTranslateX(150);
        button.setStyle("-fx-border-color: #B8E7E1; -fx-background-color: #159895; -fx-background-radius: 50px; "
                + "-fx-border-radius: 50px; -fx-text-fill: white;");
        pane.getChildren().add(button);
        StackPane.setAlignment(button, Pos.CENTER_LEFT);

        button.setOnAction(event -> {
            playerForm(stage);
        });

        // Player Vs Computer button
        Button computerButton = new Button("Player Vs Computer");
        computerButton.setScaleX(2.4);
        computerButton.setScaleY(2.4);
        computerButton.setTranslateX(-150);
        computerButton.setStyle("-fx-border-color: #B8E7E1; -fx-background-color: #159895; -fx-background-radius: 50px; "
                + "-fx-border-radius: 50px; -fx-text-fill: white;");
        pane.getChildren().add(computerButton);
        computerButton.setOnAction(event -> {
            computerForm(stage);
        });
        StackPane.setAlignment(computerButton, Pos.CENTER_RIGHT);

        Scene scene = new Scene(pane, screenWidth, screenHeight);
        stage.setTitle("Nine Men's Morris");
        stage.setScene(scene);
        stage.show();
    }

    public void letterTransition() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(LETTER_APPEARANCE, event -> {
            if (currentLetterIndex <= HEADING.length()) {
                String partialText = HEADING.substring(0, currentLetterIndex);
                headingText.setText(partialText);
                currentLetterIndex++;
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Pop-up form to get the names of the players
     *
     * @param primaryStage is the platform to show the application
     */
    public void playerForm(Stage primaryStage) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Names");
        stage.initOwner(primaryStage);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(25);
        gridPane.setHgap(10);

        Label player1Label = new Label("Player 1 name: ");
        TextField player1TextField = new TextField();
        player1TextField.setPrefColumnCount(20);
        Font playerFont = Font.font("Arial", FontWeight.BOLD, 15);
        player1Label.setFont(playerFont);

        Label player2Label = new Label("Player 2 name: ");
        TextField player2TextField = new TextField();
        player2Label.setFont(playerFont);

        gridPane.addRow(0, player1Label, player1TextField);
        gridPane.addRow(1, player2Label, player2TextField);

        Button submitButton = new Button("Start Game");
        submitButton.setScaleX(1.4);
        submitButton.setScaleY(1.4);
        submitButton.setTranslateX(275);
        submitButton.setStyle("-fx-border-color: #B8E7E1; -fx-background-color: #609966; -fx-background-radius: 25px; "
                + "-fx-border-radius: 50px; -fx-text-fill: white;");

        submitButton.setOnAction(event -> {
            if (player1TextField.getText().isEmpty() | player2TextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid player names");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid player names");
                alert.showAndWait();
            } else {
                player1Name = player1TextField.getText();
                player2Name = player2TextField.getText();
                Game game = new Game(new DoublePlayer(player1Name, player2Name));
                Display display = new Display(game);
                display.start(primaryStage);
                stage.close();
            }
        });
        gridPane.addRow(2, submitButton);
        Scene scene = new Scene(gridPane, 400, 200);
        stage.setScene(scene);
        stage.showAndWait();
    }
    public void computerForm(Stage primaryStage) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Player Name");
        stage.initOwner(primaryStage);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);
        gridPane.setHgap(10);

        Label player1Label = new Label("Player name: ");
        TextField player1TextField = new TextField();
        player1TextField.setPrefColumnCount(20);
        Font playerFont = Font.font("Arial", FontWeight.BOLD, 15);
        player1Label.setFont(playerFont);
        gridPane.addRow(0, player1Label, player1TextField);
        Button submitButton = new Button("Start Game");
        submitButton.setScaleX(1.4);
        submitButton.setScaleY(1.4);
        submitButton.setTranslateX(275);
        submitButton.setStyle("-fx-border-color: #B8E7E1; -fx-background-color: #609966; -fx-background-radius: 25px; "
                + "-fx-border-radius: 50px; -fx-text-fill: white;");

        submitButton.setOnAction(event -> {
            if (player1TextField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid player names");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid player names");
                alert.showAndWait();
            } else {
                player1Name = player1TextField.getText();
                Game game = new Game(new SinglePlayer(player1Name, "Computer"));
                Display display = new Display(game);
                display.start(primaryStage);
                stage.close();
            }
        });
        gridPane.addRow(2, submitButton);
        Scene scene = new Scene(gridPane, 400, 150);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
