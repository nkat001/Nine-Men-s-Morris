package game.Actor;

import game.Action.*;
import game.MakeTokenMovable;
import game.Position;
import game.Token;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player {
    private String name ;
    private ArrayList<Token> tokens;
    private Action allowableAction;
    private Boolean allTokensPlaced;

    public Player(String name, Color color){
        this.name= name ;
        this.allTokensPlaced= false;
        this.tokens = new ArrayList<>();
        setUpTokens(color);
    }

    public void setUpTokens(Color c){
        for (int i = 0; i <9; i++) {
            Token t = new Token(c);
            new MakeTokenMovable(t, this);
            this.tokens.add(t);
        }
    }

    public void setAllowableAction(Action allowableAction) {
        this.allowableAction = allowableAction;
    }

    public Boolean checkAction (Token selectedT, Position initP, Position finalPos){
        if (!allTokensPlaced)
        {
            // check place action first , if all token has position dy then set other action
            for (int i = 0 ; i< tokens.size(); i++){
                if(!tokens.get(i).getHasPosition()){
                    allowableAction= new Place();
                    allTokensPlaced= false;
                    break ;
                }
                allTokensPlaced= true;
                System.out.println("done placing");
            }
        }

        // all finish placing , slide action
        if(allTokensPlaced){
            System.out.println("all tokens are placed on the board dy ----------------------");
            // check if player left with how many tokens
            if(tokens.size()==3){
                System.out.println("wrong one bro");
                allowableAction= new Jump();
            }

            // TODO: implement method to check whether 3 tokens are in a row
            else if (allTokensPlaced) {
                System.out.println("remove step provided");
                allowableAction = new Remove();



            }

            else {
                System.out.println("definitely the wrong one bro");
                allowableAction= new Slide();
            }
        }
        System.out.println("the command is");
        Boolean ret  = allowableAction.execute(selectedT,initP, finalPos );

        return ret ;
    }
    public void isPlayerTurn(){
        // allow all the tokens from player repositiory to move
        for (Token token : tokens){
            token.setIsTokenAllow(true);
        }
    }
    public void notPlayerTurn(){
        // allow all the tokens from player repositiory to move
        for (Token token : tokens){
            token.setIsTokenAllow(false);
        }
    }

    public String getName() {
        return name;
    }

    public Token getTokenAt(int i){
        return tokens.get(i);
    }

}

