package game.Actor;

import game.Mode;
import game.ResetPlayerTurn;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


/**
 * @author Ethel
 *
 * modified by: Nethara
 */
public class SinglePlayer implements Mode {

    private static final Font playerFont = Font.font("Arial", FontWeight.BOLD, 30);
    private Player p1, p2;
    private static Label p1Label, p2Label;

    /**
     * Constructor
     * @param p1Name player 1 name
     * @param p2Name player 2 name
     */
    public SinglePlayer(String p1Name, String p2Name) {
        // create player instances
        p1 = new Player(p1Name, Color.PINK);
        p2 = new Player(p2Name, Color.BLUE);

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
        p1Label.setFont(playerFont);
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

        // set p2 label in the screen
        p2Label = new Label("Computer");
        p2Label.setTextFill(Color.LIGHTGREEN);
        p2Label.setFont(playerFont);
        p2Label.setBackground(background);
        StackPane.setMargin(p2Label, new Insets(200));
        StackPane.setAlignment(p2Label, Pos.TOP_RIGHT);
    }

    /**
     * run method
     * allow the game to start running the logic
     */
    public void run() {
        // set the initial game status
        ResetPlayerTurn.setMode(this);
        ResetPlayerTurn.setPlayer1(p1);
        ResetPlayerTurn.setPlayer2(p2);
        p1.isPlayerTurn();
        p2.notPlayerTurn();
    }

    /**
     * get player 1
     */
    public Player getP1() {
        return p1;
    }
    /**
     * get player 2
     */
    public Player getP2() {
        return p2;
    }

    /**
     * get player 1 label on UI
     */
    public Label getP1Label() {
        return p1Label;
    }

    /**
     * get player 1 label on UI
     */
    public Label getP2Label() {
        return p2Label;
    }

}
