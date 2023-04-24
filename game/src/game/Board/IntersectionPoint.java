package game.Board;

import game.Status;

import java.util.ArrayList;

public class IntersectionPoint {

    private static IntersectionPoint ip;
    private ArrayList<Position> listIP = new ArrayList<>();

    private IntersectionPoint(){}

    public void addListIP(Position p){
        listIP.add(p);
    }
    public static IntersectionPoint getInstance(){
        if (ip ==null){
            ip= new IntersectionPoint();
        }
        return ip ;
    }

    public ArrayList<Position> getListIP(){
        return (this.listIP);
    }



}
