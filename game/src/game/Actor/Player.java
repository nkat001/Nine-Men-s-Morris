package game.Actor;

import game.Action.*;
import game.MakeTokenMovable;
import game.Position;
import game.Token;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * @author Ethel
 *
 * modified by: Nethara , Mahesh
 */
public class Player {

    private String name ;
    private ArrayList<Token> tokens;
    private Action allowableAction;
    private Boolean allTokensPlaced;

    /**
     * Constructor
     * @param name player name
     * @param color color representing for the player tokens
     */
    public Player(String name, Color color){
        this.name= name ;
        this.allTokensPlaced= false;
        this.tokens = new ArrayList<>();
        setUpTokens(color);
    }

    /**
     * setting up the tokens for the player
     * @param c : color representing for the player tokens
     */
    public void setUpTokens(Color c){
        for (int i = 0; i <9; i++) {
            Token t = new Token(c);
            // applying actions on each token
            new MakeTokenMovable(t, this);
            this.tokens.add(t);
        }
    }

    /**
     * checking the action performed by player
     * @param selectedT: selected token by the player
     * @param initP: initial token position
     * @param finalPos : new token position
     * @param removeToken : is token a remove token
     * @return Boolean
     */
    public Boolean checkAction (Token selectedT, Position initP, Position finalPos, Boolean removeToken) {
        // check if is a remove token action
        if (removeToken){
            allowableAction= new Remove();
        }
        else
        {
            // check is all the tokens of the player placed on board
            if (!allTokensPlaced){
                for (int i = 0 ; i < tokens.size(); i++){
                    // if token has no position , allowable action is place action
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

            // if all tokens are placed
            else {
                // check is tokens left with 3
                if(tokens.size()==3){
                    allowableAction= new Jump();
                }
                else {
                    allowableAction= new Slide();
                }
            }

        }
        // execute checking on the allowable action
        Boolean ret  = allowableAction.execute(selectedT,initP, finalPos );
        return ret ;
    }

    /**
     * set is player turn
     */
    public void isPlayerTurn(){
        for (Token token : tokens){
            token.setIsTokenAllow(true);
        }
    }

    /**
     * set is not player turn
     */
    public void notPlayerTurn(){
        for (Token token : tokens){
            token.setIsTokenAllow(false);
        }
    }

    /**
     * get player name
     */
    public String getName() {
        return name;
    }

    /**
     * get the token at i index
     * @param i : int
     * @return Token
     */
    public Token getTokenAt(int i){
        return tokens.get(i);
    }

    /**
     * get token size
     * @return int
     */
    public int getTokenSize(){
        return this.tokens.size();
    }
    public ArrayList<Token> getTokens(){
        return this.tokens;
    }

    /**
     * remove token from player tokens
     * @param token to be removed
     */
    public void removeToken(Token token){
        this.tokens.remove(token);
    }
}

