package game.Actor;


import game.Mode;
import game.ResetPlayerTurn;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


// singleton , only one double player can be created and kept updated

/**
 * @author Ethel
 * <p>
 * modified by: Nethara
 */
public class DoublePlayer implements Mode {
    private static Font playerFont = Font.font("Arial", FontWeight.BOLD, 30);
    private Player p1, p2;
    private static Label p1Label, p2Label;

    /**
     * @param p1Name
     * @param p2Name
     */
    public DoublePlayer(String p1Name, String p2Name) {
        p1 = new Player(p1Name, Color.PINK);
        p2 = new Player(p2Name, Color.BLUE);

        // set p1 label in the screen
        if (!p1.getName().equals("")) {
            p1Label = new Label(p1.getName());
            p1Label.setTextFill(Color.RED);
        } else {
            p1Label = new Label("Player 1");
            p1Label.setTextFill(Color.RED);
        }

        p1Label.setFont(playerFont);
        StackPane.setMargin(p1Label, new Insets(200));
        StackPane.setAlignment(p1Label, Pos.TOP_LEFT);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.30), p1Label);
        translateTransition.setFromY(0);
        translateTransition.setToY(5);
        translateTransition.setInterpolator(Interpolator.EASE_BOTH);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(10);
        translateTransition.play();

        // set p2 label in the screen
        if (!p2.getName().equals("")) {
            p2Label = new Label(p2.getName());
        } else {
            p2Label = new Label("Player 2");
        }
        p2Label.setFont(playerFont);
        StackPane.setMargin(p2Label, new Insets(200));
        StackPane.setAlignment(p2Label, Pos.TOP_RIGHT);
    }

    public void run() {
        ResetPlayerTurn.setMode(this);
        ResetPlayerTurn.setPlayer1(p1);
        ResetPlayerTurn.setPlayer2(p2);
        p1.isPlayerTurn();
        p2.notPlayerTurn();
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public Label getP1Label() {
        return p1Label;
    }

    public Label getP2Label() {
        return p2Label;
    }
}
