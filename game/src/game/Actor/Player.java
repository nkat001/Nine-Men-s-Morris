package game.Actor;

import game.Token;

import java.util.ArrayList;

public class Player {
    private String name ;
    private char dispChar;
    private ArrayList<Token> tokens;

    public Player(String name, char dispChar){
        this.name= name ;
        this.dispChar = dispChar ;
        this.tokens = new ArrayList<Token>();
        setUpTokens();
    }
    public void setUpTokens(){
        for (int i = 0; i <9; i++) {
            Token t = new Token(this.dispChar);
            this.tokens.add(t);
        }
    }

}

