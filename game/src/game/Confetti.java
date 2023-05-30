package game;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Confetti {
    private static final int NUM_PARTICLES = 200;
    private static final double MIN_SIZE = 2;
    private static final double MAX_SIZE = 6;
    private static final double MIN_SPEED = 1;
    private static final double MAX_SPEED = 4;
    private final Group particles;

    public Confetti(double x, double y) {
        particles = new Group();

        Random random = new Random();
        for (int i = 0; i < NUM_PARTICLES; i++) {
            double size = MIN_SIZE + random.nextDouble() * (MAX_SIZE - MIN_SIZE);
            double speed = MIN_SPEED + random.nextDouble() * (MAX_SPEED - MIN_SPEED);

            Circle circle = new Circle(size, generateColor());
            circle.setCenterX(x);
            circle.setCenterY(y);

            double angle = Math.toRadians(random.nextDouble() * 360);
            double dx = Math.cos(angle) * speed;
            double dy = Math.sin(angle) * speed;
            circle.setUserData(new double[]{dx, dy});
            particles.getChildren().add(circle);
        }
    }

    public Group getParticles(){
        return particles;
    }

    public Color generateColor(){
        Random random = new Random();
        int red = random.nextInt(240);
        int green = random.nextInt(180);
        int blue = random.nextInt(230);
        return Color.rgb(red, green, blue);
    }

    public void moveParticles(){
        for (Node node : particles.getChildren()) {
            double[] velocity = (double[]) node.getUserData();
            node.setTranslateX(node.getTranslateX() + velocity[0]);
            node.setTranslateY(node.getTranslateY() + velocity[1]);
            velocity[1] += 0.05;
        }
    }
}
