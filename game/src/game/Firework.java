package game;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;

public class Firework {
    private static final int NUM_PARTICLES = 200;
    private static final double MIN_SIZE = 2;
    private static final double MAX_SIZE = 6;
    private static final double MIN_SPEED = 1;
    private static final double MAX_SPEED = 4;

    private final Group particles;

    public Firework(double x, double y) {
        particles = new Group();

        Random random = new Random();
        for (int i = 0; i < NUM_PARTICLES; i++) {
            double size = MIN_SIZE + random.nextDouble() * (MAX_SIZE - MIN_SIZE);
            double speed = MIN_SPEED + random.nextDouble() * (MAX_SPEED - MIN_SPEED);

            Circle particle = new Circle(size, getRandomColor());
            particle.setCenterX(x);
            particle.setCenterY(y);

            double angle = Math.toRadians(random.nextDouble() * 360);
            double dx = Math.cos(angle) * speed;
            double dy = Math.sin(angle) * speed;

            particle.setUserData(new double[]{dx, dy});

            particles.getChildren().add(particle);
        }
    }

    public Group getParticles() {
        return particles;
    }

    private Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(240);
        int g = random.nextInt(180);
        int b = random.nextInt(240);
        return Color.rgb(r, g, b);
    }

    public void moveParticles() {
        for (Node particle : particles.getChildren()) {
            double[] velocity = (double[]) particle.getUserData();
            particle.setTranslateX(particle.getTranslateX() + velocity[0]);
            particle.setTranslateY(particle.getTranslateY() + velocity[1]);
            velocity[1] += 0.05; // Apply gravity-like effect
        }
    }
}
