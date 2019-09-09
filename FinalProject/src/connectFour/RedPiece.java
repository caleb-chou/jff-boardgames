package connectFour;

import java.awt.image.*;
import finalproject.*;

public class RedPiece implements Piece{
    public RedPiece(){
        
    }
    
    public BufferedImage getIcon(){
        return Resources.getImage("Red.png");
    }
    
    public String getColor(){
        return "RED";
    }
    
    public RedPiece clone(){
        RedPiece c = new RedPiece();
        return c;
    }
}
