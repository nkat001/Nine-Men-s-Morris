package game.Actor;

import game.Action.*;
import game.Token;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player {
    private String name ;
    private ArrayList<Token> tokens;

    public Player(String name, Color color){
        this.name= name ;
        this.tokens = new ArrayList<>();
        setUpTokens(color);
    }

    public void setUpTokens(Color c){
        for (int i = 0; i <9; i++) {
            Token t = new Token(c);
            this.tokens.add(t);
        }
    }


    public String getName() {
        return name;
    }

    public Token getTokenAt(int i){
        return tokens.get(i);
    }

}

