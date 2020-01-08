package chess;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import finalproject.*;

public class Tile extends JButton {
    Color background;
    Color hovered;
    Color pressed;
    
    public static final double PIECE_SIZE = 0.8;
    public static final int SIZE = 100;
    
    Color square1b = new Color(180, 140, 80);
    Color square1h = new Color(165, 125, 70);
    Color square1p = new Color(150, 110, 60);
    Color square2b = new Color(250, 230, 180);
    Color square2h = new Color(230, 210, 160);
    Color square2p = new Color(210, 190, 140);
    
    int x;
    int y;
    
    Piece piece;
    boolean hasPiece = false;
    boolean isMove = false;
    boolean isTake = false;
    boolean isSelected = false;
    boolean isLastMove = false;
    boolean isCheck = false;
    
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        initializeUI();
    }
    
    private void initializeUI(){
        setBorder(null);
        setFocusPainted(false);
        setActionCommand(x + "" + y);
        if((x + y) % 2 == 0){
            background = square1b;
            hovered = square1h;
            pressed = square1p;
        }
        else{
            background = square2b;
            hovered = square2h;
            pressed = square2p;
        }
        setBackground(background);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);
        addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent evt) {
                    if (getModel().isPressed()) {
                        setBackground(pressed);
                    } else if(getModel().isRollover()){
                        setBackground(hovered);
                    } else {
                        setBackground(background);
                    }
                }
            });
    }
    
     public void update(){
        ImageIcon tile = new ImageIcon();
        if(hasPiece){
            tile = Resources.scaleImage(piece.getIcon(), (int) (PIECE_SIZE * SIZE));
        }
        if(isMove){
            ImageIcon shade = Resources.scaleImage(Resources.getImage("Moves.png"), SIZE);
            tile = Resources.layerImage(shade, tile, SIZE, SIZE);
        }
        if(isTake){
            ImageIcon shade = Resources.scaleImage(Resources.getImage("Take.png"), SIZE);
            tile = Resources.layerImage(shade, tile, SIZE, SIZE);
        }
        if(isSelected){
            ImageIcon shade = Resources.scaleImage(Resources.getImage("Select.png"), SIZE);
            tile = Resources.layerImage(shade, tile, SIZE, SIZE);
        }
        if(isCheck){
            ImageIcon shade = Resources.scaleImage(Resources.getImage("Check.png"), SIZE);
            tile = Resources.layerImage(shade, tile, SIZE, SIZE);
        }
        if(isLastMove){
            ImageIcon shade = Resources.scaleImage(Resources.getImage("Move.png"), SIZE);
            tile = Resources.layerImage(shade, tile, SIZE, SIZE);
        }
        setIcon(tile);
    }
     
     public void select(){
        isSelected = true;
    }
    
    public void deselect(){
        isSelected = false;
    }
    
    public void makeMove(){
        isMove = true;
    }
    
    public void removeMove(){
        isMove = false;
    }
    
    public void makeTake(){
        isTake = true;
    }
    
    public void removeTake(){
        isTake = false;
    }
    
    public void makeLastMove(){
        isLastMove = true;
    }
    
    public void removeLastMove(){
        isLastMove = false;
    }
    
    public void makeCheck(){
        isCheck = true;
    }
    
    public void removeCheck(){
        isCheck = false;
    }
    
    public void addPiece(Piece piece){
        this.piece = piece;
        hasPiece = true;
    }
    
    public void removePiece(){
        hasPiece = false;
    }
    
    public boolean hasPiece(){
        return hasPiece;
    }
    
    public Piece getPiece(){
        return piece;
    }
    
    public boolean hasObstruction(String color){
        return hasPiece && getPiece().color.equals(color);
    }
    
    public String toString(){
        return "" + toBase26(x) + (y + 1);
    }
    
    public static String toBase26(int n){
        if(n == 0) return "a";
        String base26 = "";
        while(n > 0){
            base26 = (char) (n % 26 + 97) + base26;
            n /= 26;
        }
        return base26;
    }
    
    public boolean equals(Tile tile){
        return tile.x == x && tile.y == y;
    }
    
    public static boolean inBounds(int x, int y){
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }
    
    public Dimension getPreferredSize(){
        return new Dimension(SIZE, SIZE);
    }
}
