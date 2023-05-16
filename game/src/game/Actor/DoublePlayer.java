package game.Actor;

import game.Action.Place;
import game.Board;
import game.Mode;
import game.ResetPlayerTurn;
import game.Token;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


// singleton , only one double player can be created and kept updated
public class DoublePlayer implements Mode {
    private static Font playerFont = Font.font("Arial", FontWeight.BOLD, 30);
    private Player p1, p2;
    private Label p1Label, p2Label ;

    public DoublePlayer(){

    }
    public DoublePlayer(String p1Name , String p2Name ){
        p1 = new Player(p1Name, Color.PINK);
        p2 = new Player( p2Name, Color.BLUE);

        // set p1 label in the screen
        if (!p1.getName().equals("")) {
            p1Label= new Label(p1.getName());
        }
        else {
            p1Label= new Label("Player 1");
        }

        p1Label.setFont(playerFont);
        StackPane.setMargin(p1Label, new Insets(200));
        StackPane.setAlignment(p1Label, Pos.TOP_LEFT);

        // set p2 label in the screen
        if (!p2.getName().equals("")){
            p2Label= new Label(p2.getName());
        }
        else {
            p2Label= new Label("Player 2");
        }
        p2Label.setFont(playerFont);
        StackPane.setMargin(p2Label, new Insets(200));
        StackPane.setAlignment(p2Label, Pos.TOP_RIGHT);
    }

    public void run(){
        System.out.println("in runnnn");
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
