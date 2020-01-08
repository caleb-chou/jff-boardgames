package chess;

import java.awt.*;
import javax.swing.*;
import finalproject.*;

public class End extends JFrame {
    Game game;
    int end;
    
    JLabel score;
    JLabel winner;
    
    JPanel actions;
    
    customButton restart;
    customButton undo;
    customButton abort;
    
    public End(Game game, int end){
        Resources.playAudio("Start_End.wav");
        this.game = game;
        this.end = end;
        
        setBounds(0, 0, 600, 250);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        
        String winnerLabel = "";
        String scoreLabel = "";
        switch(end){
            case Game.WHITE_CHECKMATE: setTitle("Black Checkmates!"); 
                                       scoreLabel = "0-1";
                                       winnerLabel = "Checkmate. Black is victorious";
                                       break;
            case Game.BLACK_CHECKMATE: setTitle("White Checkmates!");
                                       scoreLabel = "1-0";
                                       winnerLabel = "Checkmate. White is victorious";
                                       break;
            case Game.STALEMATE: setTitle("Stalemate!");
                                 scoreLabel = "1/2-1/2";
                                 winnerLabel = "Stalemate";
                                 break;
            case Game.DRAW: setTitle("Draw!");
                            scoreLabel = "1/2-1/2";
                            winnerLabel = "Draw";
                            break;
            case Game.WHITE_RESIGN: setTitle("White Resigned!");
                                    scoreLabel = "0-1";
                                    winnerLabel = "White resigned. Black is victorious";
                                    break;
            case Game.BLACK_RESIGN: setTitle("Black Resigned!");
                                    scoreLabel = "1-0";
                                    winnerLabel = "Black resigned. White is victorious";
                                    break;
        }
        
        score = new JLabel(scoreLabel);
        score.setFont(new Font("", Font.PLAIN, 20));
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        winner = new JLabel(winnerLabel);
        winner.setFont(new Font("", Font.PLAIN, 30));
        winner.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        actions = new JPanel();
        actions.setLayout(new GridLayout(1, 3));
        
            restart = new customButton(new Color(220, 220, 220), new Color(180, 180, 180), new Color(200, 200, 200));
            restart.setBorder(null);
            restart.setFont(new Font("", Font.PLAIN, 25));
            restart.setText("Restart?");
            restart.setAlignmentX(Component.CENTER_ALIGNMENT);
            restart.setActionCommand("RestartE");
            restart.addActionListener(game);

            undo = new customButton("Undo_Button", 40, new Color(220, 220, 220), new Color(180, 180, 180), new Color(200, 200, 200));
            undo.setBackground(new Color(220, 220, 220));
            undo.setBorderPainted(false);
            undo.setAlignmentX(Component.CENTER_ALIGNMENT);
            undo.addActionListener(game);
            undo.setActionCommand("UndoE");

            abort = new customButton("Abort_Button", 20, new Color(220, 220, 220), new Color(180, 180, 180), new Color(200, 200, 200));
            abort.setBorderPainted(false);
            abort.setAlignmentX(Component.CENTER_ALIGNMENT);
            abort.addActionListener(game);
            abort.setActionCommand("AbortE");
            
        actions.add(restart);
        actions.add(undo);
        actions.add(abort);
        
        JLabel filler = new JLabel(" ");
        filler.setFont(new Font("", Font.PLAIN, 50));
        add(filler);
        add(score);
        add(winner);
        JLabel filler1 = new JLabel(" ");
        filler1.setFont(new Font("", Font.PLAIN, 30));
        add(filler1);
        add(actions);
        
        setVisible(true);
    }
    
}
