package chess;

import javax.swing.*;
import finalproject.*;
import java.util.*;
import java.awt.*;

public class MoveList extends JPanel{
    public static final Color color = new Color(120, 120, 120);
    public static final Color textColor = new Color(200, 200, 200);
    
    JLabel white;
    JLabel whiteAdvantage;
    JLabel black;
    JLabel blackAdvantage;
    JTextArea moveList;
    JScrollPane scroller;
    
    JPanel actions;
    customButton undo;
    customButton resign;
    customButton draw;
    
    customButton albertMove;
    
    ArrayList<Move> moves;
    
    Game game;
    
    public MoveList(ArrayList<Move> moves, Game game){
        
        this.moves = moves;
        this.game = game;
        
        setBackground(Game.color);
            
            whiteAdvantage = new JLabel();
            whiteAdvantage.setFont(new Font("", Font.PLAIN, 30));
            whiteAdvantage.setAlignmentX(Component.LEFT_ALIGNMENT);
            whiteAdvantage.setPreferredSize(new Dimension(300, 50));
            whiteAdvantage.setForeground(textColor);
            
            blackAdvantage = new JLabel();
            blackAdvantage.setFont(new Font("", Font.PLAIN, 30));
            blackAdvantage.setAlignmentX(Component.LEFT_ALIGNMENT);
            blackAdvantage.setPreferredSize(new Dimension(300, 50));
            blackAdvantage.setForeground(textColor);
            
            moveList = new JTextArea (18, 17);
            moveList.setEditable(false);
            moveList.setBackground(color);
            moveList.setFont(new Font("", Font.PLAIN, 20));
            
            scroller = new JScrollPane(moveList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            
            actions = new JPanel();
            actions.setLayout(new GridLayout(1, 3));
            
                undo = new customButton("Undo_Button", 70);
                undo.setBorderPainted(false);
                undo.setBackground(Game.color);
                undo.addActionListener(game);
                undo.setActionCommand("Undo");
                
                draw = new customButton("Draw_Button", 70);
                draw.setBorderPainted(false);
                draw.setBackground(Game.color);
                draw.addActionListener(game);
                draw.setActionCommand("Draw");
                
                resign = new customButton("Resign_Button", 70);
                resign.setBorderPainted(false);
                resign.setBackground(Game.color);
                resign.addActionListener(game);
                resign.setActionCommand("Resign");
                
            actions.add(undo);
            actions.add(draw);
            actions.add(resign);
                
        black = new JLabel(" Black");
        black.setForeground(textColor);
        black.setPreferredSize(new Dimension(300, 30));
        black.setFont(new Font("", Font.PLAIN, 20));
        black.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        white = new JLabel(" White");
        white.setForeground(textColor);
        white.setPreferredSize(new Dimension(300, 30));
        white.setFont(new Font("", Font.PLAIN, 20));
        white.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        Color background = new Color(200, 200, 200);
        Color hovered = new Color(180, 180, 180);
        Color pressed = new Color(160, 160, 160);
        
        albertMove = new customButton(background, hovered, pressed);
        albertMove.setBorderPainted(false);
        albertMove.setText("Press for Computer");
        albertMove.setFont(new Font("", Font.PLAIN, 20));
        albertMove.addActionListener(game);
        albertMove.setActionCommand("COMPUTER");
        albertMove.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(black);
        add(blackAdvantage);
        add(scroller);
        add(white);
        add(whiteAdvantage);
        add(albertMove);
        add(actions);
    }
    
    public void update(){
        moveList.setText(null);
        if(moves.size() % 2 == 0){
            for(int i = 0; i < moves.size(); i += 2){
                moveList.append((i / 2 + 1) + ". " + moves.get(i).toString() + "   " + moves.get(i + 1).toString() + "\n");
            }
            white.setFont(new Font("", Font.BOLD, 30));
            black.setFont(new Font("", Font.PLAIN, 20));
        } else {
            for(int i = 0; i < moves.size() - 1; i += 2){
                moveList.append((i / 2 + 1) + ". " + moves.get(i).toString() + "   " + moves.get(i + 1).toString() + "\n");
            }
            moveList.append((moves.size() / 2 + 1) + ". " + moves.get(moves.size() - 1).toString());
            white.setFont(new Font("", Font.PLAIN, 20));
            black.setFont(new Font("", Font.BOLD, 30));
        }
        
        int[] whitePieceCount = new int[5];
        for(int i = 0; i < 5; i++){
            whitePieceCount[i] = 0;
        }
        for(Piece p : game.white.pieces){
            switch(p.name){
                case "Pawn": whitePieceCount[0]++; break; 
                case "Knight": whitePieceCount[1]++; break;
                case "Bishop": whitePieceCount[2]++; break;
                case "Rook": whitePieceCount[3]++; break;
                case "Queen": whitePieceCount[4]++;
            }
        }
        
        int[] blackPieceCount = new int[5];
        for(int i = 0; i < 5; i++){
            blackPieceCount[i] = 0;
        }
        for(Piece p : game.black.pieces){
            switch(p.name){
                case "Pawn": blackPieceCount[0]++; break;
                case "Knight": blackPieceCount[1]++; break;
                case "Bishop": blackPieceCount[2]++; break;
                case "Rook": blackPieceCount[3]++; break;
                case "Queen": blackPieceCount[4]++;
            }
        }
        int[] piecesDifference = new int[5];
        for(int i = 0; i < 5; i++){
            piecesDifference[i] = whitePieceCount[i] - blackPieceCount[i];
        }
        String whiteAdvantage = "";
        String blackAdvantage = "";
        for(int i = 0; i < 5; i++){
            if(piecesDifference[i] > 0){
                for(int j = 0; j < piecesDifference[i]; j++){
                    switch(i){
                        case 0: whiteAdvantage += Pawn.toString("White"); break;
                        case 1: whiteAdvantage += Knight.toString("White"); break;
                        case 2: whiteAdvantage += Bishop.toString("White"); break;
                        case 3: whiteAdvantage += Rook.toString("White"); break;
                        case 4: whiteAdvantage += Queen.toString("White");
                    }
                }
            } else {
                for(int j = 0; j < -piecesDifference[i]; j++){
                    switch(i){
                        case 0: blackAdvantage += Pawn.toString("Black"); break;
                        case 1: blackAdvantage += Knight.toString("Black"); break;
                        case 2: blackAdvantage += Bishop.toString("Black"); break;
                        case 3: blackAdvantage += Rook.toString("Black"); break;
                        case 4: blackAdvantage += Queen.toString("Black");
                    }
                }
            }
        }
        
        int whiteValue = game.white.calculateValue();
        int blackValue = game.black.calculateValue();
        int difference = whiteValue - blackValue;
        if(difference < 0){
            blackAdvantage = " +" + (-difference) + blackAdvantage;
        } else if(difference != 0) {
            whiteAdvantage = " +" + difference + whiteAdvantage;
        }
        
        this.whiteAdvantage.setText(whiteAdvantage);
        this.blackAdvantage.setText(blackAdvantage);
    }
    
    public Dimension getPreferredSize(){
        return new Dimension(300, 6 * Tile.SIZE);
    }
}
