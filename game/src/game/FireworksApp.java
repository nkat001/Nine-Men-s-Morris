package game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class FireworksApp extends Application {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int NUM_FIREWORKS = 10;

    private List<Firework> fireworks;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle("Fireworks");
        primaryStage.setScene(scene);
        primaryStage.show();

        fireworks = new ArrayList<>();

        // Create firework explosions
        for (int i = 0; i < NUM_FIREWORKS; i++) {
            double x = Math.random() * WINDOW_WIDTH;
            double y = Math.random() * WINDOW_HEIGHT;
            Firework firework = new Firework(x, y);
            fireworks.add(firework);
            root.getChildren().addAll(firework.getParticles());
        }

        // Start the fireworks animation
        startFireworksAnimation();
    }

    private void startFireworksAnimation() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), event -> {
            for (Firework firework : fireworks) {
                firework.moveParticles();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // Call this method when the player wins the game
    private void showFireworksEffect() {
        // Add additional logic here if needed

        // Show fireworks effect
        for (Firework firework : fireworks) {
            double x = Math.random() * WINDOW_WIDTH;
            double y = Math.random() * WINDOW_HEIGHT;
            firework.getParticles().setTranslateX(x);
            firework.getParticles().setTranslateY(y);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
