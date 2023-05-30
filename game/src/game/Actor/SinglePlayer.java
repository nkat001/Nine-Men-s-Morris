package game.Actor;
import game.Mode;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class SinglePlayer implements Mode {

    private static Label p1Label;
    private Player p1;
    public SinglePlayer(String p1Name) {
        // create player instances
        p1 = new Player(p1Name, Color.PINK);

        // setting the UI for double player
        BackgroundFill backgroundFill = new BackgroundFill(Color.DARKBLUE, new CornerRadii(7), null);
        Background background = new Background(backgroundFill);

        // set p1 label in the screen
        if (!p1.getName().equals("")) {
            p1Label = new Label(p1.getName());
            p1Label.setTextFill(Color.WHITE);
        } else {
            p1Label = new Label("Player 1");
            p1Label.setTextFill(Color.WHITE);
        }
        p1Label.setBackground(background);
        StackPane.setMargin(p1Label, new Insets(200));
        StackPane.setAlignment(p1Label, Pos.TOP_LEFT);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.30), p1Label);
        translateTransition.setFromY(0);
        translateTransition.setToY(5);
        translateTransition.setInterpolator(Interpolator.EASE_BOTH);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(10);
        translateTransition.play();
    }


    /**
     * run method to run game logic
     */
    public void run(){
    }

    /**
     * get player 1 method
     */
    public Player getP1(){
        return new Player("aaa", Color.BLUE );
    }

    /**
     * get player 2 method
     */
    public Player getP2()
    {
        return new Player("bbb", Color.BLUE );

    }

    /**
     * get player 1 label method
     */
    public Label getP1Label()
    {
        return p1Label;
    }

    @Override
    public Label getP2Label() {
        return null;
    }
}
