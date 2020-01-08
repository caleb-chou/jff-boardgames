package chess;

import java.awt.image.*;
import java.util.*;

public abstract class Piece {
    int x;
    int y;
    String name;
    Player player;
    String color;
    
    boolean isTaken;
    
    boolean remove;
    
    int value;
    
    public Piece(String name, Player player, int x, int y, int value){
        this.x = x;
        this.y = y;
        this.name = name;
        this.player = player;
        this.value = value;
        color = player.color;
        isTaken = false;
        remove = false;
    }
    
    public void take(){
        isTaken = true;
    }
    
    public void unTake(){
        isTaken = false;
    }
    
    public void remove(){
        remove = true;
    }
    
    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public abstract BufferedImage getIcon();
    public abstract String toString();
    public abstract ArrayList<Move> getMoves(Game game);
    public abstract ArrayList<Move> getCompMoves(Game game);
    public abstract ArrayList<Move> getCheckingMoves(Game game);
    
}
