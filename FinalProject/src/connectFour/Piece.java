package connectFour;

import java.awt.image.*;

public interface Piece{
    public BufferedImage getIcon();
    public String getColor();
    public Piece clone();
}
