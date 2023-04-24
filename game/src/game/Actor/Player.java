package game.Actor;

import game.Action.*;
import game.Token;

import java.util.ArrayList;

public class Player {
    private String name ;
    private char dispChar;
    private ArrayList<Token> tokens;
    private Action allowableAction ;

    public Player(String name, char dispChar){
        this.name= name ;
        this.dispChar = dispChar ;
        this.tokens = new ArrayList<Token>();
    }

    public void setUpTokens(){
        for (int i = 0; i <9; i++) {
            Token t = new Token(this.dispChar);
            this.tokens.add(t);
        }
    }

    public void setUpAllowableAction() {
        // when player haven't fully placed only can have placed action
        for ( int i = 0 ; i<9 ;i++){
            if(!tokens.get(i).getHasPosition()){
                allowableAction = new Place();
                break ;
            }
        }
        if (allowableAction ==null) {
            if (tokens.size()== 3 ){
                allowableAction= new Jump();
            }
            else{
                allowableAction= new Slide();
            }
        }
    }
    

}

