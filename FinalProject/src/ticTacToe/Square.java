package ticTacToe;

import java.awt.*;
import finalproject.*;

public class Square extends customButton {
    public static final int SIZE = 200;
    public static final double PIECE_SIZE = 0.8;
    
    public static final Color backgroundC = new Color(240, 240, 240);
    public static final Color pressedC = new Color(200, 200, 200);
    public static final Color hoveredC = new Color(220, 220, 220);
    
    Game game;
    
    int index;
    
    String character;
    
    public Square(Game game, int index){
        super(backgroundC, pressedC, hoveredC);
        this.game = game;
        this.index = index;
        character = "";
        
        setActionCommand("" + index);
        addActionListener(game);
    }
    
    public void update(){
        switch(character){
            case "X": setIcon(Resources.scaleImage(Resources.getImage("X.png"), (int) (PIECE_SIZE * SIZE))); break;
            case "O": setIcon(Resources.scaleImage(Resources.getImage("O.png"), (int) (PIECE_SIZE * SIZE))); break;
        }
    }
    
    public boolean hasPiece(){
        return !character.equals("");
    }
    
    public void setPiece(String piece){
        character = piece;
    }
    
    public Dimension getPreferredSize(){
        return new Dimension(SIZE, SIZE);
    }
    
}
