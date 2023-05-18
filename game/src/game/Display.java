package game;

import game.Actor.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

/**
 * Display class that creates the UI elements on the board upon selection of gamemode.
 *
 * Created by:
 *
 * @author Nethara
 * Modified by : Ethel Lim Jia Yee
 */
public class Display extends Application {
    private Game game ;

    public Display(Game game) {
        this.game = game ;
    }

    public void start(Stage primaryStage) {
        // Create a Pane to hold the circle and line groups
        StackPane mainPane = new StackPane();
        Label headingLabel = new Label("Nine Men's Morris");
        Font font = Font.font("Verdica", FontWeight.BOLD, 50);
        headingLabel.setFont(font);
        headingLabel.setStyle("-fx-text-fill: #0A4D68;");
        StackPane.setAlignment(headingLabel, Pos.TOP_CENTER);
        headingLabel.setTranslateY(25);
        mainPane.getChildren().add(headingLabel);
        mainPane.setStyle("-fx-background-color: #B9EDDD;"); // Use any valid CSS color value

        // adding the board UI to the mainpane
        Group gameBoard=game.getBoard().getGameBoard();
        mainPane.getChildren().add(gameBoard);

        // adding players label in the main pane
        Mode mode = game.getMode();
        mainPane.getChildren().add(mode.getP1Label());
        mainPane.getChildren().add(mode.getP2Label());

        Player p1 = mode.getP1();
        Player p2= mode.getP2();

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
            StackPane.setMargin(circle, new Insets(spacing * i, 260, 170, 250)); // Adjust the vertical margin for each circle
            StackPane.setAlignment(circle, Pos.CENTER_RIGHT);
        }

        for (int i = 0; i < 5; i++) {
            Circle circle = p2.getTokenAt(i).getToken();
            mainPane.getChildren().add(circle);
            StackPane.setMargin(circle, new Insets(spacing * i, 210, 170, 250)); // Adjust the vertical margin for each circle
            StackPane.setAlignment(circle, Pos.CENTER_RIGHT);
        }

        // creating a screen
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
        Platform.runLater(() -> game.run());
    }

}