package connectFour;

import java.awt.image.*;
import finalproject.*;

public class YellowPiece implements Piece{
    public YellowPiece(){
        
    }
    
    public BufferedImage getIcon(){
        return Resources.getImage("Yellow.png");
    }
    
    public String getColor(){
        return "YELLOW";
    }
    
    public YellowPiece clone(){
        YellowPiece c = new YellowPiece();
        return c;
    }
}
