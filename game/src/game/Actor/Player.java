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

    public Boolean checkAction (Token selectedT, Position initP, Position finalPos, boolean threeFound) {

        if (!allTokensPlaced)
        {
            // check place action first , if all token has position dy then set other action
            for (int i = 0 ; i < tokens.size(); i++){

                System.out.println("num of tokens: " + tokens.size());
                System.out.println("i value: " + i);
                if(!tokens.get(i).getHasPosition()){
                    allowableAction = new Place();
                    allTokensPlaced = false;

                    if (i == 9) {
                        allTokensPlaced = true;
                    }

                    break;
                }
                System.out.println("all tokens have been placed");
                allTokensPlaced= true;
                System.out.println("status of all tokens placed:" + allTokensPlaced);
            }
        }

        // all finish placing , slide action
        System.out.println("status of all tokens placed: " + allTokensPlaced);
        if(allTokensPlaced){

            if(tokens.size()==3){
                System.out.println("entered jump action performance ");
                allowableAction= new Jump();
            }

            // TODO: implement method to check whether 3 tokens are in a row
            else if (threeFound) {
                System.out.println("entered remove action performance ");
                allowableAction = new Remove();

            }

            else {
                System.out.println("entered slide action performance ");
                allowableAction= new Slide();
            }
        }

        System.out.println("Action to be performed: " + allowableAction);
        Boolean ret  = allowableAction.execute(selectedT,initP, finalPos );

        return ret ;
    }
    public void isPlayerTurn(){
        for (Token token : tokens){
            token.setIsTokenAllow(true);
        }
    }
    public void notPlayerTurn(){
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

