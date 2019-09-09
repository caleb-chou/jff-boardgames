package ticTacToe;

import javax.swing.*;
import java.awt.*;
import finalproject.*;

public class Main extends JFrame{
    public static final int NONE = -1;
    public static final int PVP = 0;
    public static final int PVC = 1;
    public static final int CVP = 2;
    
    
    JButton playerButton;
    JButton computerButton;
    JButton computer2Button;
    JButton startButton;
    
    int mode = NONE;
    
    boolean start = false;
    
    Game game;
    
    public Main(Game game){
        this.game = game;
        initializeUI();
    }
    
    private void initializeUI(){
        setBounds(0, 0, 500, 300);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        
        
        JLabel title = new JLabel();
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setText("TIC TAC TOE");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel label1 = new JLabel();
        label1.setVerticalTextPosition(JLabel.CENTER);
        label1.setFont(new Font("Arial", Font.PLAIN, 20));
        label1.setText("The classic three in a row game");
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel label2 = new JLabel();
        label2.setVerticalTextPosition(JLabel.CENTER);
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        label2.setText("Click on a square to place a piece");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel label3 = new JLabel();
        label3.setVerticalTextPosition(JLabel.CENTER);
        label3.setFont(new Font("Arial", Font.PLAIN, 20));
        label3.setText("Get three in a row to win!");
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel buttons = new JPanel();
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        playerButton = new JButton();
        playerButton.setActionCommand("Player");
        playerButton.addActionListener(game);
        playerButton.setText(" Player v Player ");
        playerButton.setPreferredSize(new Dimension(200, 200));
        computerButton = new JButton();
        computerButton.setActionCommand("Computer");
        computerButton.addActionListener(game);
        computerButton.setText("Player v Computer");
        computerButton.setBounds(50, 0, 100, 20);
        computer2Button = new JButton();
        computer2Button.setActionCommand("Computer2");
        computer2Button.addActionListener(game);
        computer2Button.setText("Computer v Player");
        computer2Button.setBounds(50, 0, 100, 20);
        
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
        buttons.add(playerButton);
        buttons.add(computerButton);
        buttons.add(computer2Button);
        
        updateButton();
        
        startButton = new JButton();
        startButton.setActionCommand("Start");
        startButton.addActionListener(game);
        startButton.setText("Start");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(title);
        add(label1);
        add(label2);
        add(label3);
        add(buttons);
        add(startButton);
        
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setIconImage(Resources.getImage("TicTacToe_Icon.png"));
        setVisible(true);
    }
    
    public void setPlayer(){
        mode = PVP;
    }
    
    public void setComputer(){
        mode = PVC;
    }
    
    public void setComputer2(){
        mode = CVP;
    }
    
    public boolean hasInput(){
        return start;
    }
    
    public void updateButton(){
        switch(mode){
            case NONE: playerButton.setBackground(Color.red);
                       computerButton.setBackground(Color.red); 
                       computer2Button.setBackground(Color.red); break;
            case PVP:  playerButton.setBackground(Color.green);
                       computerButton.setBackground(Color.red);
                       computer2Button.setBackground(Color.red); break;
            case PVC:  playerButton.setBackground(Color.red);
                       computerButton.setBackground(Color.green); 
                       computer2Button.setBackground(Color.red); break;
            case CVP:  playerButton.setBackground(Color.red);
                       computerButton.setBackground(Color.red); 
                       computer2Button.setBackground(Color.green); break;
        }
    }
    
    public void updateCommand(String button){
        switch(button){
            case "Player": setPlayer(); break;
            case "Computer": setComputer(); break;
            case "Computer2": setComputer2(); break;
        }
        updateButton();
    }
}