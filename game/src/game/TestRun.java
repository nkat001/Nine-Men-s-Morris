package game;

import game.Actor.DoublePlayer;
import game.Actor.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TestRun extends Application {
    private final Board board = Board.getInstance();

    public void start(Stage primaryStage) {
        // Create a Pane to hold the circle and line groups
        StackPane mainPane = new StackPane();
        Label headingLabel = new Label("Nine Men's Morris");
        Font font = Font.font("Comic Sans MS", FontWeight.BOLD, 45);
        headingLabel.setFont(font);
        headingLabel.setStyle("-fx-text-fill: #CD0404;");
        StackPane.setAlignment(headingLabel, Pos.TOP_CENTER);
        headingLabel.setTranslateY(20);
        mainPane.getChildren().add(headingLabel);
        mainPane.setStyle("-fx-background-color: #FFF6C3;"); // Use any valid CSS color value

        DoublePlayer dbMode = new DoublePlayer();
        mainPane.getChildren().add(dbMode.getP1Label());
        mainPane.getChildren().add(dbMode.getP2Label());

        Player p1 = dbMode.getP1();
        Player p2= dbMode.getP2();


        double spacing = 100;

        // tokens for player 1
        for (int i = 0; i < 5; i++) {
            Circle circle = p1.getTokenAt(i).getToken();
            mainPane.getChildren().add(circle);
            StackPane.setMargin(circle, new Insets(spacing * i, 200, 170, 210)); // Adjust the vertical margin for each circle
            StackPane.setAlignment(circle, Pos.CENTER_LEFT);
        }

        for (int i = 0; i < 4; i++) {
            Circle circle = p1.getTokenAt(i+ 5).getToken();
            mainPane.getChildren().add(circle);
            StackPane.setMargin(circle, new Insets(spacing * i, 200, 170, 260)); // Adjust the vertical margin for each circle
            StackPane.setAlignment(circle, Pos.CENTER_LEFT);
        }

        // tokens for player 2
        for (int i = 0; i < 4; i++) {
            Circle circle = p2.getTokenAt(i+ 5).getToken();
            mainPane.getChildren().add(circle);
            StackPane.setMargin(circle, new Insets(spacing * i, 270, 170, 250)); // Adjust the vertical margin for each circle
            StackPane.setAlignment(circle, Pos.CENTER_RIGHT);
        }

        for (int i = 0; i < 5; i++) {
            Circle circle = p2.getTokenAt(i).getToken();
            mainPane.getChildren().add(circle);
            StackPane.setMargin(circle, new Insets(spacing * i, 220, 170, 250)); // Adjust the vertical margin for each circle
            StackPane.setAlignment(circle, Pos.CENTER_RIGHT);
        }

        Group gameBoard=board.getGameBoard();

        // Add the circle and line groups to the main pane
        mainPane.getChildren().addAll(gameBoard);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();

        // Create a scene and add the main pane to it
        Scene scene = new Scene(mainPane, screenWidth, screenHeight);

        // Set the stage properties and show it
        primaryStage.setTitle("Nine Men's Morris");
        primaryStage.setScene(scene);
        primaryStage.show();
        Platform.runLater(() -> dbMode.run());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
