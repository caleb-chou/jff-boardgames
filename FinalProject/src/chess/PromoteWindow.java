package chess;

import javax.swing.*;
import java.awt.*;
import finalproject.*;

public class PromoteWindow extends JFrame {
    JLabel label;
    JPanel pieces;
        JButton Queen;
        JButton Rook;
        JButton Bishop;
        JButton Knight;
        
    Color color = new Color(240, 240, 240);
        
    public PromoteWindow(Move move){
        setBounds(0, 0, 4 * Tile.SIZE, Tile.SIZE + 25);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setTitle("Promotion");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setOpacity(0.8f);
        setAlwaysOnTop(true);
        
        label = new JLabel("Choose Promotion Piece:");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        pieces = new JPanel();
        pieces.setLayout(new GridLayout(1, 4));
        
            Queen = new JButton();
            Queen.setActionCommand("Queen");
            Queen.addActionListener(move);
            Queen.setBackground(color);
            Rook = new JButton();
            Rook.setActionCommand("Rook");
            Rook.addActionListener(move);
            Rook.setBackground(color);
            Bishop = new JButton();
            Bishop.setActionCommand("Bishop");
            Bishop.addActionListener(move);
            Bishop.setBackground(color);
            Knight = new JButton();
            Knight.setActionCommand("Knight");
            Knight.addActionListener(move);
            Knight.setBackground(color);
            
            if(move.origP.color.equals("White")){
                Queen.setIcon(Resources.scaleImage(Resources.getImage("White_Queen.png"), (int) (Tile.PIECE_SIZE * Tile.SIZE)));
                Rook.setIcon(Resources.scaleImage(Resources.getImage("White_Rook.png"), (int) (Tile.PIECE_SIZE * Tile.SIZE)));
                Bishop.setIcon(Resources.scaleImage(Resources.getImage("White_Bishop.png"), (int) (Tile.PIECE_SIZE * Tile.SIZE)));
                Knight.setIcon(Resources.scaleImage(Resources.getImage("White_Knight.png"), (int) (Tile.PIECE_SIZE * Tile.SIZE)));
            } else {
                Queen.setIcon(Resources.scaleImage(Resources.getImage("Black_Queen.png"), (int) (Tile.PIECE_SIZE * Tile.SIZE)));
                Rook.setIcon(Resources.scaleImage(Resources.getImage("Black_Rook.png"), (int) (Tile.PIECE_SIZE * Tile.SIZE)));
                Bishop.setIcon(Resources.scaleImage(Resources.getImage("Black_Bishop.png"), (int) (Tile.PIECE_SIZE * Tile.SIZE)));
                Knight.setIcon(Resources.scaleImage(Resources.getImage("Black_Knight.png"), (int) (Tile.PIECE_SIZE * Tile.SIZE)));
            }
        pieces.add(Queen);
        pieces.add(Rook);
        pieces.add(Bishop);
        pieces.add(Knight);
        
        add(label);
        add(pieces);
        setVisible(true);
    }
    
}
