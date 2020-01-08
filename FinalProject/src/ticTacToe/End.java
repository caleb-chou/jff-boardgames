package ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import finalproject.*;

public class End extends JFrame {
    
    int winner;
    
    JPanel filler;
    JLabel winnerLabel;
    JLabel playAgain;
    JPanel buttons;
    customButton yes;
    customButton no;
    
    Game game;
    
    public End(Game game, int winner){
        this.game = game;
        this.winner = winner;
        initializeUI();
    }
    
    private void initializeUI(){
        setBounds(0, 0, 600, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));
        setResizable(false);
        setAlwaysOnTop(true);
        
        Color c;
        switch(winner){
            case Game.X_WIN: c = new Color(255, 255, 255); break;
            case Game.O_WIN: c = new Color(150, 150, 150); break;
            case Game.DRAW: c = new Color(200, 200, 200); break;
            default: c = Color.WHITE; break;
        }
        
        filler = new JPanel();
        filler.setBackground(c);
        
        add(filler);
        
        winnerLabel = new JLabel("", SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        winnerLabel.setOpaque(true);
        
        switch(winner){
            case Game.X_WIN: winnerLabel.setText("X WINS!"); break;
            case Game.O_WIN: winnerLabel.setText("O WINS!"); break;
            case Game.DRAW: winnerLabel.setText("IT'S A DRAW!"); break;
            default: winnerLabel.setText("IT'S A DRAW!"); break;
        }
        
        
        winnerLabel.setBackground(c);
        add(winnerLabel);
        
        playAgain = new JLabel("Play Again?", SwingConstants.CENTER);
        playAgain.setFont(new Font("Arial", Font.BOLD, 20));
        playAgain.setBackground(c);
        playAgain.setOpaque(true);
        add(playAgain);
        
        buttons = new JPanel();
        buttons.setBackground(c);
        yes = new customButton(Color.GREEN, new Color(0, 150, 0), new Color(0, 200, 0));
        yes.setBorderPainted(false);
        yes.setText("Yes");
        yes.addActionListener(game);
        yes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                dispose();
            }
        });
        yes.setActionCommand("Restart");
        no = new customButton(Color.RED, new Color(150, 0, 0), new Color(200, 0, 0));
        no.setBorderPainted(false);
        no.setText("No");
        no.addActionListener(game);
        no.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                dispose();
            }
        });
        no.setActionCommand("No");
        buttons.add(yes);
        buttons.add(no);
        
        add(buttons);
        
        setVisible(true);
    }
    
}
