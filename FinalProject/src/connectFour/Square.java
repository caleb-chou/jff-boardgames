package connectFour;

import javax.swing.*;
import java.awt.*;
import finalproject.*;

public class Square extends JButton{
    public static final int SIZE = 100;
    
    boolean hasPiece;
    Piece piece;
    
    int column;
    
    public Square(int column){
        this.column = column;
        hasPiece = false;
        setActionCommand("" + column);
        setBorder(null);
        setBackground(new Color(255, 255, 255));
    }
    
    public void addPiece(Piece piece){
        hasPiece = true;
        this.piece = piece;
    }
    
    public void update(){
        if(hasPiece){
            setIcon(new ImageIcon(piece.getIcon()));
        } else {
            setIcon(new ImageIcon(Resources.getImage("Empty.png")));
        }
    }
    
    public String getColor(){
        if(hasPiece) return piece.getColor();
        return "NONE";
    }
    
    public Dimension getPreferredSize(){
        return new Dimension(SIZE, SIZE);
    }
    
    public boolean equals(Square square){
        return hasPiece && square.hasPiece && piece.getColor().equals(square.getColor());
    }
    
    public String toString(){
        if(!hasPiece) return "empty";
        return piece.getColor();
    }
    
    public void restart(){
        hasPiece = false;
    }
    
    public Square clone(){
        Square c = new Square(column);
        if(hasPiece){
            c.addPiece(piece.clone());
        }
        return c;
    }
}
