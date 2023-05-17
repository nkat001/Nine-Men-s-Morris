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

    public Boolean checkAction (Token selectedT, Position initP, Position finalPos, Boolean removetoken) {
        if (removetoken){
            allowableAction= new Remove();
        }
        else
        {
            if (!allTokensPlaced){
                // check place action first , if all token has position dy then set other action
                for (int i = 0 ; i < tokens.size(); i++){
                    if(!tokens.get(i).getHasPosition()){
                        allowableAction = new Place();
                        allTokensPlaced = false;
                        if (i == 9) {
                            allTokensPlaced = true;
                        }
                        break;
                    }
                    allTokensPlaced= true;
                }
            }
            else {
                if(tokens.size()==3){
                    System.out.println("entered jump action performance ");
                    allowableAction= new Jump();
                }
                else {
                    System.out.println("entered slide action performance ");
                    allowableAction= new Slide();
                }
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
    public int getTokenSize(){
        return this.tokens.size();
    }

    public void removeToken(Token token){
        this.tokens.remove(token);
    }
}

