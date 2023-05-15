package game;

import game.Actor.DoublePlayer;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Home extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane pane = new StackPane();

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

        Label label = new Label("Nine Men's Morris");
        Font font = Font.font("Impact", FontWeight.BOLD, 80);
        label.setFont(font);
        label.setStyle("-fx-text-fill: black; -fx-effect: dropshadow(gaussian, white, 1, 1, 1, 1);");
        StackPane.setAlignment(label, Pos.TOP_CENTER);
        label.setTranslateY(20);

        label.setOpacity(0);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(4), label);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        pane.getChildren().add(label);

        // Player Vs Player button
        Button button = new Button("Player Vs Player");
        button.setScaleX(2.5);
        button.setScaleY(2.5);
        button.setTranslateX(150);
        button.setStyle("-fx-border-color: #B8E7E1; -fx-background-color: #159895; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-text-fill: white;");
        pane.getChildren().add(button);
        StackPane.setAlignment(button, Pos.CENTER_LEFT);

        button.setOnAction(event -> {
            // mode == double player
            Game game = new Game(new DoublePlayer());
            Display display= new Display(game );
            display.start(stage);
        });

        // Player Vs Computer button
        Button computerButton = new Button("Player Vs Computer");
        computerButton.setScaleX(2.4);
        computerButton.setScaleY(2.4);
        computerButton.setTranslateX(-150);
        computerButton.setStyle("-fx-border-color: #B8E7E1; -fx-background-color: #159895; -fx-background-radius: 50px; -fx-border-radius: 50px; -fx-text-fill: white;");
        pane.getChildren().add(computerButton);
        StackPane.setAlignment(computerButton, Pos.CENTER_RIGHT);

        Scene scene = new Scene(pane, screenWidth, screenHeight);
        stage.setTitle("Nine Men's Morris");
        stage.setScene(scene);
        stage.show();
    }
}
